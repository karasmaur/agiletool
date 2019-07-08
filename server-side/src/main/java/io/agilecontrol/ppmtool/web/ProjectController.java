package io.agilecontrol.ppmtool.web;

import io.agilecontrol.ppmtool.Domain.Project;
import io.agilecontrol.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("")
    public ResponseEntity<Project> createNewProject(@RequestBody Project project){

        Project returnProject = projectService.saveOrUpdateProject(project);

        return new ResponseEntity<Project>(returnProject, HttpStatus.CREATED);
    }
}
