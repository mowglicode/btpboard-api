package io.btpboard.persistance.repository;

import io.btpboard.persistance.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientRepository extends JpaRepository<Client, Long> {
}
