package io.btpboard.service.impl;

import io.btpboard.dto.ClientDTO;
import io.btpboard.persistance.entity.Client;
import io.btpboard.service.IClientService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ClientServiceTest {

    @Autowired
    IClientService service;

    Client client1;
    ClientDTO client1DTO;

    @Before
    public void setUp() throws Exception {
        client1 = new Client();
        client1.setCity("Basse-Terre");
        client1.setCode("BTTEST01");
        client1.setCompanyName("Eurl Gwada Construction");
        client1.setZipcode(97100);
        client1.setSiret(4587985463225L);
        client1.setSociety(true);
        client1DTO = service.save(client1);
    }

    @After
    public void tearDown() throws Exception {
        service.delete(client1DTO.getId());
    }

    @Test
    public void save() {
        client1DTO = service.findOne(client1.getId());
        assertEquals(client1.getSiret(), client1DTO.getSiret());
    }

    @Test
    public void findAll() {
        Client client2 = new Client();
        client2.setCity("Petit-Bourg");
        client2.setCode("PBTEST02");
        client2.setCompanyName("SAS Minatchy Construction");
        client2.setZipcode(97170);
        client2.setSiret(7887985463225L);
        client2.setSociety(true);
        service.save(client2);

        List<ClientDTO> clientDTOS = service.findAll();
        assertEquals(2, clientDTOS.size());
        assertEquals(client2.getSiret(), clientDTOS.get(1).getSiret());
        service.delete(client2.getId());

    }

    @Test
    public void findOne() {
        Client client2 = new Client();
        client2.setCity("Petit-Bourg");
        client2.setCode("PBTEST02");
        client2.setCompanyName("SAS Minatchy Construction");
        client2.setZipcode(97170);
        client2.setSiret(7887985463225L);
        client2.setSociety(true);
        service.save(client2);
        ClientDTO clientDTO = service.findOne(client2.getId());
        assertEquals(client2.getSiret(), clientDTO.getSiret());
        service.delete(client2.getId());
    }

    @Test
    public void delete() {
        Client client2 = new Client();
        client2.setCity("Petit-Bourg");
        client2.setCode("PBTEST02");
        client2.setCompanyName("SAS Minatchy Construction");
        client2.setZipcode(97170);
        client2.setSiret(7887985463225L);
        client2.setSociety(true);
        service.save(client2);

        List<ClientDTO> clientDTOS = service.findAll();
        assertEquals(2, clientDTOS.size());
        service.delete(client2.getId());
        clientDTOS = service.findAll();
        assertEquals(1, clientDTOS.size());
    }
}