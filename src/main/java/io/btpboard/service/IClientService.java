package io.btpboard.service;

import io.btpboard.dto.ClientDTO;
import io.btpboard.persistance.entity.Client;

import java.util.List;

public interface IClientService {

    public ClientDTO save(Client client);
    public List<ClientDTO> findAll();
    public ClientDTO findOne(long id);
    public void delete(long id);
}
