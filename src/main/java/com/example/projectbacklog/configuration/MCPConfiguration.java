package com.example.projectbacklog.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.projectbacklog.mcptools.MCPService;

@Configuration
public class MCPConfiguration {

    @Bean
    public ToolCallbackProvider weatherTools(MCPService weatherService) {
        return MethodToolCallbackProvider.builder().toolObjects(weatherService).build();
    }
}
