package com.ahmedmq.llm.rag

import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor
import org.springframework.ai.reader.ExtractedTextFormatter
import org.springframework.ai.reader.pdf.PagePdfDocumentReader
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig
import org.springframework.ai.transformer.splitter.TokenTextSplitter
import org.springframework.ai.vectorstore.SearchRequest
import org.springframework.ai.vectorstore.VectorStore
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service

@Service
class LLmRagService(chatClientBuilder: ChatClient.Builder, val vectorStore: VectorStore) {

    val chatClient = chatClientBuilder.build()

    fun load(resource: Resource) {
        val pdfReader = createPdfReader(resource)
        val splitDocuments = splitAndAnnotateDocuments(pdfReader, resource.filename)
        vectorStore.accept(splitDocuments)
    }

    private fun createPdfReader(resource: Resource) =
        PagePdfDocumentReader(
            resource,
            PdfDocumentReaderConfig.builder()
                .withPageExtractedTextFormatter(
                    ExtractedTextFormatter.builder()
                        .withNumberOfBottomTextLinesToDelete(3)
                        .withNumberOfTopPagesToSkipBeforeDelete(1)
                        .build(),
                )
                .withPagesPerDocument(1)
                .build(),
        )

    private fun splitAndAnnotateDocuments(pdfReader: PagePdfDocumentReader, filename: String?) =
        TokenTextSplitter().apply(pdfReader.get()).onEach { document ->
            document.metadata["filename"] = filename
            document.metadata["version"] = 1
        }

    fun query(question: String): String {
//        val advisorSearchRequest = SearchRequest
//            .defaults()
//            .withTopK(2)
//            .withSimilarityThreshold(0.7)

        val prompt = """
            Here is the question \n{question}\n
            Please respond in only Japanese
        """.trimIndent()

        return chatClient.prompt()
            .user { u -> u.text(prompt).param("question", question) }
            .advisors(QuestionAnswerAdvisor(vectorStore, SearchRequest.defaults()))
            .call()
            .content()
    }
}
