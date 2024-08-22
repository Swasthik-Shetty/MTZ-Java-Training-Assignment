package com.monetize360.jpal;

import com.monetize360.jpal.models.Author;
import com.monetize360.jpal.repository.AuthorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpaLearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaLearningApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(
		AuthorRepository authorRepository
	){
		return args -> {
			var author = Author.builder()
					.firstName("John")
					.lastName("Doe")
					.age(50)
					.email("johndoe@example.com")
					.build();
			authorRepository.save(author);
		};
	}

}
