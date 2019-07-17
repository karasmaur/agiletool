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
        //The try/catch below might be wrong in a sense that it's too broad, maybe an if statement would be better.
        try{
            project.setIdentifier(project.getIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e){
            throw new ProjectIdException("Project ID '"+project.getIdentifier().toUpperCase()+"' already exists!");
        }
    }

    public Project findProjectByIdentifier(String identifier){

        Project project = projectRepository.findByIdentifier(identifier.toUpperCase());

        if(project == null){
           throw new ProjectIdException("Project ID '"+identifier.toUpperCase()+"' doesn't exist!");
        }

        return project;
    }

    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String identifier){
        Project project = projectRepository.findByIdentifier(identifier.toUpperCase());

        if(project == null){
            throw new ProjectIdException("Project ID '"+identifier.toUpperCase()+"' doesn't exist!");
        }

        projectRepository.delete(project);
    }
}
