package io.btpboard.controller;

import io.btpboard.dto.QuoteDTO;
import io.btpboard.persistance.entity.Quote;
import io.btpboard.service.IQuoteService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

    @Autowired
    IQuoteService service;

    @ApiOperation(value = "Get all quotes")
    @RequestMapping(method = RequestMethod.GET)
    public List<QuoteDTO> findAll() {
        return service.findAll();
    }

    @ApiOperation(value = "Get a quote by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public QuoteDTO findOne(@PathVariable long id) {
        return service.findOne(id);
    }

    @ApiOperation(value = "Save a new quote with his client")
    @RequestMapping(method = RequestMethod.POST, value = "/project/{idProject}")
    public QuoteDTO saveWithProjectId(@RequestBody Quote quote, long idProject) {
        return service.saveWithProjectId(quote, idProject);
    }

    @ApiOperation(value = "Delete a quote by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        service.delete(id);
    }

}
