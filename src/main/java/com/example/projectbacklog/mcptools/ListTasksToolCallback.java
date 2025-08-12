package com.example.projectbacklog.mcptools;

import java.util.List;

import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.definition.DefaultToolDefinition;
import org.springframework.ai.tool.definition.ToolDefinition;
import org.springframework.stereotype.Component;

import com.example.projectbacklog.model.Task;
import com.example.projectbacklog.repository.TaskRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class ListTasksToolCallback extends AbstractMongoToolCallback<Task, String> implements ToolCallback {

    public ListTasksToolCallback(TaskRepository repository) {
        super(repository);
    }

    @Override
    public ToolDefinition getToolDefinition() {
        return new DefaultToolDefinition("listTasks", "List all tasks", "{" +
                "  \"type\": \"object\",\n" +
                "  \"properties\": {\n" +
                "}" +
                "}"

        );
    }

    @Override
    public String call(String toolInput) {
        try {
            List<Task> allTasks = getRepository().findAll();
            return getObjectMapper().writeValueAsString(allTasks);
        } catch (JsonProcessingException e) {
            return "{\"error\":\"Could not serialize task list: " + e.getMessage() + "\"}";
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