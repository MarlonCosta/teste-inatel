package br.com.inatel.testmarlon.core.usecases.validators;

import br.com.inatel.testmarlon.core.entities.ExistingStock;
import br.com.inatel.testmarlon.core.errors.NonexistentStockException;
import br.com.inatel.testmarlon.core.usecases.boundaries.ExistingStocksRepository;
import br.com.inatel.testmarlon.core.usecases.boundaries.QuoteValidator;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class ExistingStockServiceValidator implements QuoteValidator {

    private final ExistingStocksRepository existingStocksRepository;

    @Override
    public void validate(String stockId) {

        List<ExistingStock> existingStocks = existingStocksRepository.getExistingStocks();

        assert existingStocks != null;
        boolean result = existingStocks.stream().map(ExistingStock::getId).collect(Collectors.toList()).contains(stockId.toLowerCase());

        if (!result) {
            throw new NonexistentStockException(String.format("Stock [%s] doesn't exists", stockId));
        }
    }

}
