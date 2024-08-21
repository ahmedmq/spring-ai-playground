package com.ahmedmq.playground.app

import com.ahmedmq.llm.rag.LLmRagService
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile

@Controller
@ComponentScan(basePackageClasses = [LLmRagService::class])
class LLmRagController(val lLmRagService: LLmRagService) {

    @PostMapping("load")
    fun load(model: Model, @RequestParam("file") file: MultipartFile): String {
        lLmRagService.load(file.resource)
        model.addAttribute("result", "Data Loaded successfully")
        return "index"
    }

    @GetMapping("/rag/chat")
    fun query(model: Model, @RequestParam("question") question: String): String {
        val answer = lLmRagService.query(question)
        model.addAttribute("answer", answer)
        return "index"
    }
}
