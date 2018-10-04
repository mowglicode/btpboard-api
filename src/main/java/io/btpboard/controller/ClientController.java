package io.btpboard.controller;

import io.btpboard.dto.ClientDTO;
import io.btpboard.persistance.entity.Client;
import io.btpboard.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    IClientService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<ClientDTO> findAll() {
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ClientDTO save(@RequestBody Client client) {
        return service.save(client);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ClientDTO findOne(@PathVariable long id) {
        return service.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        service.delete(id);
    }
}
