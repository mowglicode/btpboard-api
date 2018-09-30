package io.btpboard.service.impl;

import io.btpboard.dto.ProjectDTO;
import io.btpboard.persistance.entity.Project;
import io.btpboard.service.IProjectService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectServiceTest {

    @Autowired
    IProjectService service;

    Project project;
    ProjectDTO projectDTO;

    @Before
    public void setUp() throws Exception {
        project = new Project();
        project.setTitle("Construction Villa Minatchy");
        projectDTO = service.save(project);
    }

    @After
    public void tearDown() throws Exception {
        service.delete(project.getId());
    }

    @Test
    public void save() {
        Project project2 = new Project();
        project2.setTitle("Construction École Kourou 6");
        ProjectDTO project2DTO = service.save(project2);
        assertEquals(project2.getTitle(), project2DTO.getTitle());
    }


    @Test
    public void findOne() {
        projectDTO = service.findOne(project.getId());
        assertEquals(project.getTitle(), projectDTO.getTitle());
    }

    @Test
    public void findAll() {
        Project project2 = new Project();
        project2.setTitle("Construction École Cayenne 6");
        ProjectDTO project2DTO = service.save(project2);
        List<ProjectDTO> projectsDTO = service.findAll();
        assertEquals(2, projectsDTO.size());
        service.delete(project2DTO.getId());
    }

    @Test
    public void delete() {
        Project project2 = new Project();
        project2.setTitle("Construction École Cayenne 6");
        ProjectDTO project2DTO = service.save(project2);
        List<ProjectDTO> projectsDTO = service.findAll();
        assertEquals(2, projectsDTO.size());
        service.delete(project2DTO.getId());
        projectsDTO = service.findAll();
        assertEquals(1, projectsDTO.size());
    }
}