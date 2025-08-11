package com.example.projectbacklog.mcptools;

import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.definition.DefaultToolDefinition;
import org.springframework.ai.tool.definition.ToolDefinition;
import org.springframework.stereotype.Component;

@Component
public class TaskToolCallBack implements ToolCallback {

    @Override
    public ToolDefinition getToolDefinition() {

        ToolDefinition def = new DefaultToolDefinition("test-tool", "just a test tool", "{\n" + //
                "  \"type\": \"object\",\n" + //
                "  \"properties\": {\n" + //
                "    \"query\": {\n" + //
                "      \"type\": \"string\",\n" + //
                "      \"description\": \"The search query to execute.\"\n" + //
                "    },\n" + //
                "    \"max_results\": {\n" + //
                "      \"type\": \"number\",\n" + //
                "      \"description\": \"The maximum number of results to return.\",\n" + //
                "      \"default\": 10\n" + //
                "    },\n" + //
                "    \"category\": {\n" + //
                "      \"type\": \"string\",\n" + //
                "      \"description\": \"The category to filter search results by.\",\n" + //
                "      \"enum\": [\"news\", \"images\", \"videos\"]\n" + //
                "    }\n" + //
                "  },\n" + //
                "  \"required\": [\"query\"]\n" + //
                "}");

        return def;

    }

    @Override
    public String call(String toolInput) {
        // TODO Auto-generated method stub
        return "online";
    }

}
