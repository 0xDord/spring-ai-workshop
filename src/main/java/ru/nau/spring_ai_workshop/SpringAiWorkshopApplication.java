package ru.nau.spring_ai_workshop;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringAiWorkshopApplication {

	@Bean
	public ChatClient chatClient(ChatClient.Builder builder) {
		return builder.build();
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringAiWorkshopApplication.class, args);
		ChatClient chatClient = context.getBean(ChatClient.class);
		System.out.println(chatClient.prompt().user("Опиши отличие vibe coding от deep coding").call().content());
	}


}
