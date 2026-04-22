package ru.nau.spring_ai_workshop.chat;

import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final OllamaChatModel ollamaChatModel;

    public ChatController(OllamaChatModel ollamaChatModel) {
        this.ollamaChatModel = ollamaChatModel;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam(defaultValue = "Придумай короткую шутку о котах") String prompt) {
        return ollamaChatModel.call(prompt);
    }
}
