package br.com.inatel.testmarlon.core.usecases;

import br.com.inatel.testmarlon.core.entities.Quote;
import br.com.inatel.testmarlon.core.entities.repositories.QuoteRepository;
import br.com.inatel.testmarlon.core.usecases.boundaries.ReadAllStocksBoundary;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ReadAllStocks implements ReadAllStocksBoundary {

    private final QuoteRepository quoteRepository;

    @Override
    public List<Quote> execute(){
        return quoteRepository.getAllQuotes();
    }
}
