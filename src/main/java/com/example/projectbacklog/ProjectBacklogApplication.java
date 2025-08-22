package com.example.projectbacklog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.example.projectbacklog.hypermanager.HyperManagerProperties;

@ComponentScan(basePackages = {
		"com.example.projectbacklog.configuration",
		"com.example.projectbacklog.mcptools",
		"com.example.projectbacklog.hypermanager"
})
@EnableMongoRepositories("com.example.projectbacklog.repository")
@SpringBootApplication
@EnableConfigurationProperties(HyperManagerProperties.class)
public class ProjectBacklogApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectBacklogApplication.class, args);
	}

}
