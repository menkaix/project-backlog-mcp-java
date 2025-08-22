package com.example.projectbacklog.mcptools;

import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import com.example.projectbacklog.hypermanager.HyperManagerClient;

import java.io.IOException;

@Service
public class MCPService {

    private final HyperManagerClient hyperManagerClient;

    public MCPService(HyperManagerClient hyperManagerClient) {
        this.hyperManagerClient = hyperManagerClient;
    }

    // @Tool(description = "Get the list of the tasks inside a given project", name
    // = "GetTaskList")
    // public String getTaskList(
    // @ToolParam(description = "Identifier of the project to get the task from :
    // id, project code or project name") String identifier,
    // ToolContext toolContext) {
    // return "";
    // }

    @Tool(description = "Get the list of diagrams", name = "get-list-diagrams")
    public String getListDiagrams() throws IOException {
        return hyperManagerClient.get("/diagrams");
    }

    // @Tool(description = "gets a diagram as png", name = "get-png-diagram")
    // public String getPngDiagram(@ToolParam(description = "mongo id of the
    // diagram") String diagramName)
    // throws IOException {
    // return hyperManagerClient.get("/diagram/png/" + diagramName);
    // }

    @Tool(description = "gets a diagram as plantUML url", name = "get-plant-url-diagram")
    public String getPlantUrlDiagram(@ToolParam(description = "mongo id of the diagram") String diagramName)
            throws IOException {
        return hyperManagerClient.get("/diagram/plant-url/" + diagramName);
    }

    @Tool(description = "gets a diagram", name = "get-diagram")
    public String getDiagram(@ToolParam(description = "mongo id of the diagram") String id) throws IOException {
        return hyperManagerClient.get("/diagram/" + id);
    }

    @Tool(description = "get a lists of projects", name = "get-list-projects")
    public String getListProjects() throws IOException {
        return hyperManagerClient.get("/project-command/all");
    }

    @Tool(description = "get the components of a project in tree form", name = "get-projects-tree")
    public String getProjectsTree(@ToolParam(description = "project identifier") String project) throws IOException {
        return hyperManagerClient.get("/project-command/" + project + "/tree");
    }

    @Tool(description = "get a lists of feature-types", name = "get-list-feature-types")
    public String getListFeatureTypes() throws IOException {
        return hyperManagerClient.get("/featuretypes");
    }

    @Tool(description = "Get the story tree by story ID", name = "get-story-tree")
    public String getStoryTree(@ToolParam(description = "ID of the story") String storyID) throws IOException {
        return hyperManagerClient.get("/story-command/" + storyID + "/tree");
    }

    @Tool(description = "Refresh feature types", name = "refresh-feature-types")
    public String refreshFeatureTypes() throws IOException {
        return hyperManagerClient.get("/feature-command/refresh-types");
    }

    @Tool(description = "Normalize tasks", name = "normalize-tasks")
    public String normalizeTasks() throws IOException {
        return hyperManagerClient.get("/normalize-tasks");
    }

    @Tool(description = "Get a diagram definition", name = "get-diagram-definition")
    public String getDiagramDefinition(@ToolParam(description = "Name of the diagram") String name) throws IOException {
        return hyperManagerClient.get("/diagram/plant-definition/" + name);
    }

    @Tool(description = "creates a diagram", name = "add-diagram")
    public String addDiagram(@ToolParam(description = "diagram data in json format") String request)
            throws IOException {
        return hyperManagerClient.post("/diagrams", request);
    }

    @Tool(description = "updates a diagram and returns image", name = "update-diagram-graphic")
    public String updateDiagramGraphic(@ToolParam(description = "mongo id of the diagram") String diagramName,
            @ToolParam(description = "json body in the the updates") String request) throws IOException {
        return hyperManagerClient.patch("/diagram/update-graphic/" + diagramName, request);
    }

    @Tool(description = "updates a diagram", name = "update-diagram")
    public String updateDiagram(@ToolParam(description = "mongo id of the diagram") String id,
            @ToolParam(description = "json body in the the updates") String request) throws IOException {
        return hyperManagerClient.patch("/diagrams/" + id, request);
    }

    @Tool(description = "creates a project", name = "add-project")
    public String addProject(@ToolParam(description = "project data in json format") String request)
            throws IOException {
        return hyperManagerClient.post("/projects", request);
    }

    @Tool(description = "Update a story", name = "update-story")
    public String updateStory(@ToolParam(description = "Story data to update") String request) throws IOException {
        return hyperManagerClient.post("/story-command/update", request);
    }

    @Tool(description = "Add a feature to a story", name = "add-feature-to-story")
    public String addFeatureToStory(@ToolParam(description = "ID of the story") String story,
            @ToolParam(description = "Feature data to add") String request) throws IOException {
        return hyperManagerClient.post("/feature-command/" + story + "/add", request);
    }

    @Tool(description = "Add a child feature to a parent feature", name = "add-child-feature")
    public String addChildFeature(@ToolParam(description = "ID of the parent feature") String parent,
            @ToolParam(description = "Child feature data to add") String request) throws IOException {
        return hyperManagerClient.post("/feature-command/" + parent + "/add-child", request);
    }

    @Tool(description = "Adopt a child feature", name = "adopt-child-feature")
    public String adoptChildFeature(@ToolParam(description = "ID of the parent feature") String parent,
            @ToolParam(description = "ID of the child feature") String child) throws IOException {
        return hyperManagerClient.post("/feature-command/" + parent + "/adopt/" + child, "{}");
    }

    @Tool(description = "Add an actor to a project", name = "add-actor")
    public String addActor(@ToolParam(description = "ID of the project") String project,
            @ToolParam(description = "Actor data to add") String request) throws IOException {
        return hyperManagerClient.post("/actor-command/" + project + "/add", request);
    }

    @Tool(description = "Add a story to an actor", name = "add-story-to-actor")
    public String addStoryToActor(@ToolParam(description = "ID of the project") String project,
            @ToolParam(description = "Name of the actor") String name,
            @ToolParam(description = "Story data to add") String request) throws IOException {
        return hyperManagerClient.post("/actor-command/" + project + "/" + name + "/add-story", request);
    }

    @Tool(description = "Update a diagram definition", name = "update-diagram-definition")
    public String updateDiagramDefinition(@ToolParam(description = "Name of the diagram") String name,
            @ToolParam(description = "Diagram definition data") String request) throws IOException {
        return hyperManagerClient.patch("/diagram/update/" + name, request);
    }
}
