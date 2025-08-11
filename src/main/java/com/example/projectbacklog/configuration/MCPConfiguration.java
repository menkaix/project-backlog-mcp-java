package com.example.projectbacklog.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.projectbacklog.mcptools.TaskToolCallBack;

@Configuration
public class MCPConfiguration {

    @Autowired
    private TaskToolCallBack taskToolCallBack;

    @Bean
    public ToolCallbackProvider myTools() {
        // List<ToolCallback> tools = new ArrayList<>();
        List<ToolCallback> tools = new ArrayList<ToolCallback>();

        tools.add(taskToolCallBack);

        return ToolCallbackProvider.from(tools);
    }

}
