package com.example.projectbacklog.mcptools;

import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.definition.DefaultToolDefinition;
import org.springframework.ai.tool.definition.ToolDefinition;
import org.springframework.stereotype.Component;

import com.example.projectbacklog.model.Task;
import com.example.projectbacklog.repository.TaskRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class CreateTaskToolCallback extends AbstractMongoToolCallback<Task, String> implements ToolCallback {

    public CreateTaskToolCallback(TaskRepository repository) {
        super(repository);
    }

    @Override
    public ToolDefinition getToolDefinition() {
        return new DefaultToolDefinition("createTask", "Create a new task", "{\n" +
                "  \"type\": \"object\",\n" +
                "  \"properties\": {\n" +
                "    \"title\": {\n" +
                "      \"type\": \"string\",\n" +
                "      \"description\": \"The title of the task\"\n" +
                "    },\n" +
                "    \"description\": {\n" +
                "      \"type\": \"string\",\n" +
                "      \"description\": \"The description of the task\"\n" +
                "    },\n" +
                "    \"status\": {\n" +
                "      \"type\": \"string\",\n" +
                "      \"description\": \"The status of the task\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"required\": [\"title\", \"description\", \"status\"]\n" +
                "}");
    }

    @Override
    public String call(String toolInput) {
        try {
            Task taskToCreate = getObjectMapper().readValue(toolInput, getEntityClass());
            Task createdTask = getRepository().save(taskToCreate);
            return getObjectMapper().writeValueAsString(createdTask);
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