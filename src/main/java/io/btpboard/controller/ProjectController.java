package io.btpboard.controller;

import io.btpboard.ProjectDTO;
import io.btpboard.persistance.entity.Project;
import io.btpboard.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private IProjectService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<ProjectDTO> findAll() {
        return service.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ProjectDTO findOne(@PathVariable long id) {
        return service.findOne(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ProjectDTO save(@RequestBody Project project) {
        return service.save(project);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        service.delete(id);
    }
}
