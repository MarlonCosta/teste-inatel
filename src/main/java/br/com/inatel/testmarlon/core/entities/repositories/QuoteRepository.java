package br.com.inatel.testmarlon.core.entities.repositories;

import br.com.inatel.testmarlon.core.entities.Quote;

import java.util.List;

public interface QuoteRepository {
    List<Quote> getAllQuotes();
    List<Quote> findAllByStockId(String stockId);
    Quote createQuote(Quote quoteRequest);
}
