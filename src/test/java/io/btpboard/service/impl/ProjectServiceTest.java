package io.btpboard.service.impl;

import io.btpboard.dto.ProjectDTO;
import io.btpboard.persistance.entity.Client;
import io.btpboard.persistance.entity.Project;
import io.btpboard.service.IClientService;
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

    @Autowired
    IClientService clientService;

    Project project;
    ProjectDTO projectDTO;
    Client client;

    @Before
    public void setUp() throws Exception {
        client = new Client();
        client.setCode("TESTCLIENT");
        client.setSiret(245588662258L);
        client.setZipcode(97310);
        client.setCity("Kourou");
        clientService.save(client);
        project = new Project();
        project.setTitle("Construction Villa Minatchy");
        project.setDescription("Construction d'une Villa T4");
        project.setClient(client);
        projectDTO = service.save(project);
    }

    @After
    public void tearDown() throws Exception {
        service.delete(project.getId());
        clientService.delete(client.getId());
    }

    @Test
    public void save() {
        Project project2 = new Project();
        project2.setTitle("Construction École Kourou 6");
        project2.setDescription("Construction d'une école TCE");
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
        project2.setDescription("Construction d'une école TCE");
        ProjectDTO project2DTO = service.save(project2);
        List<ProjectDTO> projectsDTO = service.findAll();
        assertEquals(2, projectsDTO.size());
        service.delete(project2DTO.getId());
    }

    @Test
    public void delete() {
        Project project2 = new Project();
        project2.setTitle("Construction École Cayenne 6");
        project2.setDescription("Construction d'une école TCE");
        ProjectDTO project2DTO = service.save(project2);
        List<ProjectDTO> projectsDTO = service.findAll();
        assertEquals(2, projectsDTO.size());
        service.delete(project2DTO.getId());
        projectsDTO = service.findAll();
        assertEquals(1, projectsDTO.size());
    }

    @Test
    public void saveWithClientId() {

        Project project2 = new Project();
        project2.setTitle("Construction École Cayenne 6");
        project2.setDescription("Construction d'une école TCE");
        ProjectDTO project2DTO = service.saveWithClientId(project2, client.getId());
        assertEquals("Kourou", projectDTO.getClient().getCity());
        service.delete(project2.getId());
    }
}