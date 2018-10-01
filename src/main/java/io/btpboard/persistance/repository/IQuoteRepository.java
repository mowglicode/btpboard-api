package io.btpboard.persistance.repository;

import io.btpboard.persistance.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IQuoteRepository extends JpaRepository<Quote, Long> {
}
