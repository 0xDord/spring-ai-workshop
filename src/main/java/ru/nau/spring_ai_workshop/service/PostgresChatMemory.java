package ru.nau.spring_ai_workshop.service;

import jakarta.transaction.Transactional;
import lombok.Builder;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import ru.nau.spring_ai_workshop.model.Chat;
import ru.nau.spring_ai_workshop.model.ChatEntry;
import ru.nau.spring_ai_workshop.repository.ChatRepository;

import java.util.Comparator;
import java.util.List;

@Builder
public class PostgresChatMemory implements ChatMemory {

    private ChatRepository chatRepository;

    private int maxMessages;

    @Override
    @Transactional
    public void add(String conversationId, List<Message> messages) {
        Chat chat = chatRepository.findById(Long.valueOf(conversationId)).orElseThrow();
        messages.forEach(message -> {
            chat.addEntry(ChatEntry.toChatEntry(message));
        });
        chatRepository.save(chat);
    }

    @Override
    public List<Message> get(String conversationId) {
        Chat chat = chatRepository.findById(Long.valueOf(conversationId)).orElseThrow();
        return chat.getHistory().stream()
                .sorted(Comparator.comparing(ChatEntry::getCreatedAt).reversed())
                .map(ChatEntry::toMessage)
                .limit(maxMessages)
                .toList();
    }

    @Override
    public void clear(String conversationId) {

    }
}
