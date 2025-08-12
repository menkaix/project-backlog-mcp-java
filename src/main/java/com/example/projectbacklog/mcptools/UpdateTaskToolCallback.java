package com.example.projectbacklog.mcptools;

import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.definition.DefaultToolDefinition;
import org.springframework.ai.tool.definition.ToolDefinition;
import org.springframework.stereotype.Component;

import com.example.projectbacklog.model.Task;
import com.example.projectbacklog.repository.TaskRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class UpdateTaskToolCallback extends AbstractMongoToolCallback<Task, String> implements ToolCallback {

    public UpdateTaskToolCallback(TaskRepository repository) {
        super(repository);
    }

    @Override
    public ToolDefinition getToolDefinition() {
        return new DefaultToolDefinition("updateTask", "Update an existing task", "{\n" +
                "  \"type\": \"object\",\n" +
                "  \"properties\": {\n" +
                "    \"id\": {\n" +
                "      \"type\": \"string\",\n" +
                "      \"description\": \"The ID of the task to update\"\n" +
                "    },\n" +
                "    \"title\": {\n" +
                "      \"type\": \"string\",\n" +
                "      \"description\": \"The new title of the task\"\n" +
                "    },\n" +
                "    \"description\": {\n" +
                "      \"type\": \"string\",\n" +
                "      \"description\": \"The new description of the task\"\n" +
                "    },\n" +
                "    \"status\": {\n" +
                "      \"type\": \"string\",\n" +
                "      \"description\": \"The new status of the task\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"required\": [\"id\"]\n" +
                "}");
    }

    @Override
    public String call(String toolInput) {
        try {
            Task taskToUpdate = getObjectMapper().readValue(toolInput, getEntityClass());
            Task updatedTask = getRepository().save(taskToUpdate);
            return getObjectMapper().writeValueAsString(updatedTask);
        } catch (JsonProcessingException e) {
            return "{\"error\":\"Invalid JSON input: " + e.getMessage() + "\"}";
        } catch (Exception e) {
            return "{\"error\":\"An unexpected error occurred: " + e.getMessage() + "\"}";
        }
    }

    @Override
    protected Class<Task> getEntityClass() {
        return Task.class;
    }

    @Override
    protected Class<String> getIdClass() {
        return String.class;
    }
}