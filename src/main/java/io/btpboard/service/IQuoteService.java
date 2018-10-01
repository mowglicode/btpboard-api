package io.btpboard.service;

import io.btpboard.dto.QuoteDTO;
import io.btpboard.persistance.entity.Quote;

import java.util.List;

public interface IQuoteService {

    public List<QuoteDTO> findAll();
    public QuoteDTO save(Quote quote);
    public QuoteDTO findOne(long id);
    public void delete(long id);
    public QuoteDTO saveWithProjectId(Quote quote, long idProject);
}
