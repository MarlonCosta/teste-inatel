package br.com.inatel.testmarlon.core.usecases;

import br.com.inatel.testmarlon.core.entities.Quote;
import br.com.inatel.testmarlon.core.entities.repositories.QuoteRepository;
import br.com.inatel.testmarlon.core.usecases.boundaries.ReadStockByStockIdBoundary;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ReadStockByStockId implements ReadStockByStockIdBoundary {

    private final QuoteRepository quoteRepository;

    @Override
    public List<Quote> execute(String stockId) {
        return quoteRepository.findAllByStockId(stockId);
    }
}
