package br.com.inatel.testmarlon.core.usecases.boundaries;

import br.com.inatel.testmarlon.core.entities.Quote;

public interface CreateStockBoundary {
    Quote execute(Quote quoteRequest);
}
