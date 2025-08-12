package com.example.projectbacklog.mcptools;

import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.definition.DefaultToolDefinition;
import org.springframework.ai.tool.definition.ToolDefinition;
import org.springframework.stereotype.Component;

import com.example.projectbacklog.model.Task;
import com.example.projectbacklog.repository.TaskRepository;

@Component
public class DeleteTaskToolCallback extends AbstractMongoToolCallback<Task, String> implements ToolCallback {

    public DeleteTaskToolCallback(TaskRepository repository) {
        super(repository);
    }

    @Override
    public ToolDefinition getToolDefinition() {
        return new DefaultToolDefinition("deleteTask", "Delete a task by its ID", "{\n" +
                "  \"type\": \"object\",\n" +
                "  \"properties\": {\n" +
                "    \"id\": {\n" +
                "      \"type\": \"string\",\n" +
                "      \"description\": \"The ID of the task to delete\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"required\": [\"id\"]\n" +
                "}");
    }

    @Override
    public String call(String toolInput) {
        try {
            String idToDelete = toolInput; // Assuming the input is just the ID string
            getRepository().deleteById(idToDelete);
            return "{\"status\":\"deleted\"}";
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