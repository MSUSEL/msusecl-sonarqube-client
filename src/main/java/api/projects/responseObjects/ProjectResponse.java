package api.projects.responseObjects;

public class ProjectResponse {
    private Create project;
    
    public ProjectResponse() {}
    
    public ProjectResponse(Create project) {
        this.project = project;
    }
    
    public Create getProject() {
        return project;
    }
    
    public void setProject(Create project) {
        this.project = project;
    }
}
