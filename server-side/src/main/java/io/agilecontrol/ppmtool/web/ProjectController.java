package io.agilecontrol.ppmtool.web;

import io.agilecontrol.ppmtool.domain.Project;
import io.agilecontrol.ppmtool.services.MapValidationErrorService;
import io.agilecontrol.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationErrors(result);
        if(errorMap != null) return errorMap;

        Project returnProject = projectService.saveOrUpdateProject(project);

        return new ResponseEntity<Project>(returnProject, HttpStatus.CREATED);
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<Project> getProjectByIdentifier(@PathVariable String identifier){

        Project project = projectService.findProjectByIdentifier(identifier);

        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Project>> getAllProjects(){
        Iterable<Project> projects = projectService.findAllProjects();

        return new ResponseEntity<Iterable<Project>>(projects, HttpStatus.OK);

    }
}
