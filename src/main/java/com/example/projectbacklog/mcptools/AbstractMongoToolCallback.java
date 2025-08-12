package com.example.projectbacklog.mcptools;

import org.springframework.ai.tool.ToolCallback;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractMongoToolCallback<T, ID> implements ToolCallback {

    private final MongoRepository<T, ID> repository;
    private final ObjectMapper objectMapper;

    public AbstractMongoToolCallback(MongoRepository<T, ID> repository) {
        this.repository = repository;
        this.objectMapper = new ObjectMapper();
    }

    protected MongoRepository<T, ID> getRepository() {
        return repository;
    }

    protected ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    protected abstract Class<T> getEntityClass();

    protected abstract Class<ID> getIdClass();
}