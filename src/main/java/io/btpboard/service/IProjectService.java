package io.btpboard.service;

import io.btpboard.ProjectDTO;
import io.btpboard.persistance.entity.Project;

import java.util.List;

public interface IProjectService {

    ProjectDTO save(Project project);
    ProjectDTO findOne(long id);
    void delete(long id);
    List<ProjectDTO> findAll();
}
