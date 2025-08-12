package com.example.projectbacklog.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.projectbacklog.mcptools.CreateTaskToolCallback;
import com.example.projectbacklog.mcptools.DeleteTaskToolCallback;
import com.example.projectbacklog.mcptools.ListTasksToolCallback;
import com.example.projectbacklog.mcptools.ReadTaskToolCallback;
import com.example.projectbacklog.mcptools.UpdateTaskToolCallback;

@Configuration
public class MCPConfiguration {

    @Bean
    public ToolCallbackProvider myTools(
            CreateTaskToolCallback createTaskToolCallback,
            ReadTaskToolCallback readTaskToolCallback,
            ListTasksToolCallback listTasksToolCallback,
            UpdateTaskToolCallback updateTaskToolCallback,
            DeleteTaskToolCallback deleteTaskToolCallback) {
        List<ToolCallback> tools = new ArrayList<>();
        tools.add(createTaskToolCallback);
        tools.add(readTaskToolCallback);
        tools.add(listTasksToolCallback);
        tools.add(updateTaskToolCallback);
        tools.add(deleteTaskToolCallback);
        return ToolCallbackProvider.from(tools);
    }

}
