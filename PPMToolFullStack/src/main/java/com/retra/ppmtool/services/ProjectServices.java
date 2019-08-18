package com.retra.ppmtool.services;

import com.retra.ppmtool.domain.Project;
import com.retra.ppmtool.exceptions.ProjectIdException;
import com.retra.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServices {
    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
                throw new ProjectIdException(String.format("PROJECT ID %s already exist",
                        project.getProjectIdentifier().toUpperCase()));
        }
    }
    public Project findByProjectIdentifier(String projectId){

        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if(project==null){
            throw new ProjectIdException(String.format("PROJECT ID %s does not exist",
                    projectId));
        }
        return project;
    }
    public Iterable<Project> findAllProjects(){
      return projectRepository.findAll();
    }
    public void deleteProjectByIdentifier(String projectId){
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if(project==null){
            throw new ProjectIdException(String.format("Cannot delete PROJECT WITH ID %s",
                    projectId));
        }
        projectRepository.delete(project);
    }
}
