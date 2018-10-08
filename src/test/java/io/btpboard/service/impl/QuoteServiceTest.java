package io.btpboard.service.impl;

import io.btpboard.dto.QuoteDTO;
import io.btpboard.persistance.entity.Project;
import io.btpboard.persistance.entity.Quote;
import io.btpboard.persistance.repository.IProjectRepository;
import io.btpboard.service.IProjectService;
import io.btpboard.service.IQuoteService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuoteServiceTest {

    @Autowired
    IQuoteService service;

    @Autowired
    IProjectService projectService;

    Quote quote1;
    QuoteDTO quote1DTO;
    Date dueDate;

    @Before
    public void setUp() throws Exception {
        dueDate = new Date();
        dueDate.setMonth(dueDate.getMonth() + 1);
        quote1 = new Quote();
        quote1.setCreatedAt(new Date());
        quote1.setDueDate(dueDate);
        quote1.setNumber("TESTQ124");
        quote1.setObject("Devis de test");
        quote1.setTotalAmount(1456987.23);
        quote1DTO = service.save(quote1);
    }

    @After
    public void tearDown() throws Exception {
        service.delete(quote1.getId());
    }

    @Test
    public void findAll() {
        Quote quote2 = new Quote();
        quote2.setCreatedAt(new Date());
        quote2.setDueDate(dueDate);
        quote2.setObject("Devis 2 de test");
        quote2.setNumber("14TESTDEV");
        quote2.setTotalAmount(478965.41);
        service.save(quote2);
        List<QuoteDTO> quotesDTO = service.findAll();
        assertEquals(2, quotesDTO.size());
        service.delete(quote2.getId());
    }

    @Test
    public void save() {
        Quote quote2 = new Quote();
        quote2.setCreatedAt(new Date());
        quote2.setDueDate(dueDate);
        quote2.setObject("Devis 2 de test");
        quote2.setNumber("14TESTDEV");
        quote2.setTotalAmount(478965.41);
        QuoteDTO quote2DTO = service.save(quote2);
        assertEquals(quote2.getNumber(), quote2DTO.getNumber());
        service.delete(quote2.getId());
    }

    @Test
    public void findOne() {
        quote1DTO = service.findOne(quote1.getId());
        assertEquals(quote1.getNumber(), quote1DTO.getNumber());
    }

    @Test
    public void delete() {
        Quote quote2 = new Quote();
        quote2.setCreatedAt(new Date());
        quote2.setDueDate(dueDate);
        quote2.setObject("Devis 2 de test");
        quote2.setNumber("14TESTDEV");
        quote2.setTotalAmount(478965.41);
        QuoteDTO quote2DTO = service.save(quote2);
        List<QuoteDTO> quotesDTO = service.findAll();
        assertEquals(2, quotesDTO.size());
        service.delete(quote2.getId());
        quotesDTO = service.findAll();
        assertEquals(1, quotesDTO.size());
    }



   /* @Test
    public void saveWithProjectId() {
        Project project = new Project();
        project.setTitle("Titre test many to one");
        projectService.save(project);
        Quote quote2 = new Quote();
        quote2.setCreatedAt(new Date());
        quote2.setDueDate(dueDate);
        quote2.setObject("Devis 2 de test");
        quote2.setNumber("14TESTDEV");
        quote2.setTotalAmount(478965.41);
        QuoteDTO quote2DTO = service.saveWithProjectId(quote2, project.getId());
        assertEquals(quote2.getNumber(), quote2DTO.getNumber());
        assertEquals(project.getTitle(), quote2DTO.getProject().getTitle());
        service.delete(quote2.getId());
        projectService.delete(project.getId());
    }*/

}