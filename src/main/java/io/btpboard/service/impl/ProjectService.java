package io.btpboard.service.impl;

import io.btpboard.dto.ProjectDTO;
import io.btpboard.exception.NotFoundException;
import io.btpboard.persistance.entity.Client;
import io.btpboard.persistance.entity.Project;
import io.btpboard.persistance.repository.IClientRepository;
import io.btpboard.persistance.repository.IProjectRepository;
import io.btpboard.service.IProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService implements IProjectService {

    @Autowired
    IProjectRepository repository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    IClientRepository clientRepository;

    @Override
    public ProjectDTO save(Project project) {
        project = repository.save(project);
        return modelMapper.map(project, ProjectDTO.class);
    }

    @Override
    public ProjectDTO findOne(long id) {
        Optional<Project> tmp = repository.findById(id);
        if (tmp.isPresent()) {
            return modelMapper.map(tmp.get(), ProjectDTO.class);
        }
        throw new NotFoundException("Project not found.");
    }

    @Override
    public void delete(long id) {
        this.findOne(id);
        repository.deleteById(id);
    }

    @Override
    public List<ProjectDTO> findAll() {
        List<Project> projects = repository.findAll();
        List<ProjectDTO> projectsDTO = new ArrayList<>();
        for (Project project : projects) {
            projectsDTO.add(modelMapper.map(project, ProjectDTO.class));
        }
        return projectsDTO;
    }

    @Override
    public ProjectDTO saveWithClientId(Project project, long idClient) {
        Optional<Client> tmp = clientRepository.findById(idClient);
        if (tmp.isPresent()) {
            Client client = modelMapper.map(tmp.get(), Client.class);
            client = clientRepository.save(client);
            project.setClient(client);
            project = repository.save(project);
            return modelMapper.map(project, ProjectDTO.class);
        }

        throw new NotFoundException("Client not found, Project can't be created");
    }
}
