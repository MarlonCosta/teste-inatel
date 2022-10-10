package br.com.inatel.testmarlon.core.usecases.boundaries;

import br.com.inatel.testmarlon.core.entities.Quote;

import java.util.List;

public interface ReadStockByStockIdBoundary {
    List<Quote> execute(String stockId);
}
