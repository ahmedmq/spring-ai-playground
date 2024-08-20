package com.ahmedmq.playground.app

import com.ahmedmq.llm.rag.OllamaService
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Service

@Service
@ComponentScan(basePackageClasses = [OllamaService::class])
class ChatService(val ollamaService: OllamaService) {

}