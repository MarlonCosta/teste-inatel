package br.com.inatel.testmarlon.core.entities.mockrepositories;

import br.com.inatel.testmarlon.core.entities.Quote;
import br.com.inatel.testmarlon.core.entities.repositories.QuoteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MockQuoteRepository implements QuoteRepository {

    private final List<Quote> quotes = new ArrayList<>();

    @Override
    public List<Quote> getAllQuotes() {
        return quotes;
    }

    @Override
    public List<Quote> findAllByStockId(String stockId) {
        return quotes.stream().filter(it -> it.getStockId().equals(stockId)).collect(Collectors.toList());
    }

    @Override
    public Quote createQuote(Quote quoteRequest) {
        quotes.add(quoteRequest);
        return quoteRequest;
    }
}
