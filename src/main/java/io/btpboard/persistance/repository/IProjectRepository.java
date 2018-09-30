package io.btpboard.persistance.repository;

import io.btpboard.persistance.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProjectRepository extends JpaRepository<Project, Long> {
}
