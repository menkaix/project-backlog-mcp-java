package com.example.projectbacklog.repository;

import com.example.projectbacklog.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "tasks", path = "tasks")
public interface TaskRepository extends MongoRepository<Task, String> {
}