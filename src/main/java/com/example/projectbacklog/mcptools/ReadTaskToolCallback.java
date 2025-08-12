package com.example.projectbacklog.mcptools;

import java.util.Optional;

import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.definition.DefaultToolDefinition;
import org.springframework.ai.tool.definition.ToolDefinition;
import org.springframework.stereotype.Component;

import com.example.projectbacklog.model.Task;
import com.example.projectbacklog.repository.TaskRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class ReadTaskToolCallback extends AbstractMongoToolCallback<Task, String> implements ToolCallback {

    public ReadTaskToolCallback(TaskRepository repository) {
        super(repository);
    }

    @Override
    public ToolDefinition getToolDefinition() {
        return new DefaultToolDefinition("readTask", "Read a single task by its ID", "{\n" +
                "  \"type\": \"object\",\n" +
                "  \"properties\": {\n" +
                "    \"id\": {\n" +
                "      \"type\": \"string\",\n" +
                "      \"description\": \"The ID of the task to read\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"required\": [\"id\"]\n" +
                "}");
    }

    @Override
    public String call(String toolInput) {
        try {
            String idToRead = toolInput; // Assuming the input is just the ID string
            Optional<Task> foundEntity = getRepository().findById(idToRead);
            return getObjectMapper().writeValueAsString(foundEntity.orElse(null));
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