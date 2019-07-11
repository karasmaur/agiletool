package io.agilecontrol.ppmtool.services;

import io.agilecontrol.ppmtool.domain.Project;
import io.agilecontrol.ppmtool.exceptions.ProjectIdException;
import io.agilecontrol.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){
        try{
            project.setIdentifier(project.getIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e){
            throw new ProjectIdException("Project ID '"+project.getIdentifier().toUpperCase()+"' already exists!");
        }
    }
}
