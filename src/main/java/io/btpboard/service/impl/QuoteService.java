package io.btpboard.service.impl;

import io.btpboard.dto.QuoteDTO;
import io.btpboard.exception.NotFoundException;
import io.btpboard.persistance.entity.Quote;
import io.btpboard.persistance.repository.IQuoteRepository;
import io.btpboard.service.IQuoteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuoteService implements IQuoteService {

    @Autowired
    IQuoteRepository repository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<QuoteDTO> findAll() {
        List<Quote> quotes = repository.findAll();
        List<QuoteDTO> quotesDTO = new ArrayList<>();
        for (Quote quote : quotes) {
            quotesDTO.add(modelMapper.map(quote, QuoteDTO.class));
        }
        return quotesDTO;
    }

    @Override
    public QuoteDTO save(Quote quote) {
        quote = repository.save(quote);
        return modelMapper.map(quote, QuoteDTO.class);
    }

    @Override
    public QuoteDTO findOne(long id) {
        Optional<Quote> tmp = repository.findById(id);
        if (tmp.isPresent()) {
            return modelMapper.map(tmp.get(), QuoteDTO.class);
        }
        throw new NotFoundException("Quote not found.");
    }

    @Override
    public void delete(long id) {
        this.findOne(id);
        repository.deleteById(id);
    }
}
