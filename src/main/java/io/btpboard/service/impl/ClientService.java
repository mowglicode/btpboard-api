package io.btpboard.service.impl;

import io.btpboard.dto.ClientDTO;
import io.btpboard.exception.NotFoundException;
import io.btpboard.persistance.entity.Client;
import io.btpboard.persistance.repository.IClientRepository;
import io.btpboard.service.IClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements IClientService {

    @Autowired
    IClientRepository repository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ClientDTO save(Client client) {
        client = repository.save(client);
        return modelMapper.map(client, ClientDTO.class);
    }

    @Override
    public List<ClientDTO> findAll() {
        List<Client> clients = repository.findAll();
        List<ClientDTO> clientDTOS = new ArrayList<>();
        for (Client client : clients) {
            clientDTOS.add(modelMapper.map(client, ClientDTO.class));
        }
        return clientDTOS;
    }

    @Override
    public ClientDTO findOne(long id) {
        Optional<Client> tmp = repository.findById(id);
        if (tmp.isPresent()) {
            return modelMapper.map(tmp.get(), ClientDTO.class);
        }
        throw new NotFoundException("Client not found");
    }

    @Override
    public void delete(long id) {
        this.findOne(id);
        repository.deleteById(id);
    }
}
