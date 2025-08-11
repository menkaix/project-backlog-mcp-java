package com.example.projectbacklog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@ComponentScan(basePackages = {
		"com.example.projectbacklog.configuration",
		"com.example.projectbacklog.mcptools"
})
@EnableMongoRepositories("com.example.projectbacklog.repository")
@SpringBootApplication
public class ProjectBacklogApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectBacklogApplication.class, args);
	}

}
