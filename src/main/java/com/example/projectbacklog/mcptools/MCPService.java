package com.example.projectbacklog.mcptools;

import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

@Service
public class MCPService {

    @Tool(description = "Get the list of the tasks inside a given project", name = "GetTaskList")
    public String getTaskList(
            @ToolParam(description = "Identifier of the project to get the task from : id, project code or project name") String identifier,
            ToolContext toolContext) {
        return "";
    }

    

}
