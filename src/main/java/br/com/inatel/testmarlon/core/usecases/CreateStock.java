package br.com.inatel.testmarlon.core.usecases;

import br.com.inatel.testmarlon.core.entities.Quote;
import br.com.inatel.testmarlon.core.entities.repositories.QuoteRepository;
import br.com.inatel.testmarlon.core.usecases.boundaries.CreateStockBoundary;
import br.com.inatel.testmarlon.core.usecases.boundaries.QuoteValidator;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CreateStock implements CreateStockBoundary {

    private final QuoteRepository quoteRepository;
    private final List<QuoteValidator> quoteValidators;

    @Override
    public Quote execute(Quote quoteRequest) {
        quoteValidators.forEach(validator -> validator.validate(quoteRequest.getStockId()));
        return quoteRepository.createQuote(quoteRequest);
    }
}
