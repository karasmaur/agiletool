package io.agilecontrol.ppmtool.services;

import io.agilecontrol.ppmtool.Domain.Project;
import io.agilecontrol.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){

        //Logic goes here...

        return projectRepository.save(project);
    }
}
