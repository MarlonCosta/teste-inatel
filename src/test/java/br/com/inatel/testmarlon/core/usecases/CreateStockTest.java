package br.com.inatel.testmarlon.core.usecases;

import br.com.inatel.testmarlon.core.entities.Quote;
import br.com.inatel.testmarlon.core.entities.mockrepositories.MockExistingStocksRepository;
import br.com.inatel.testmarlon.core.entities.mockrepositories.MockQuoteRepository;
import br.com.inatel.testmarlon.core.entities.repositories.QuoteRepository;
import br.com.inatel.testmarlon.core.errors.NonexistentStockException;
import br.com.inatel.testmarlon.core.usecases.boundaries.ExistingStocksRepository;
import br.com.inatel.testmarlon.core.usecases.boundaries.QuoteValidator;
import br.com.inatel.testmarlon.core.usecases.validators.ExistingStockServiceValidator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CreateStockTest {

    private final QuoteRepository quoteRepository = new MockQuoteRepository();
    private final ExistingStocksRepository stocksRepository = new MockExistingStocksRepository();
    private final List<QuoteValidator> quoteValidators = List.of(new ExistingStockServiceValidator(stocksRepository));
    private final CreateStock createStock = new CreateStock(quoteRepository, quoteValidators);

    @Test
    void shouldCreateQuoteSuccessfully() {
        Quote quote = new Quote(
                UUID.randomUUID(),
                "VALE3",
                new HashMap<>()
        );
        assertEquals(quote, createStock.execute(quote));
    }

    @Test
    void shouldThrowBussinessExceptionForNonexistentStockId() {
        Quote quote = new Quote(
                UUID.randomUUID(),
                "VALIA",
                new HashMap<>()
        );
        assertThrows(NonexistentStockException.class, () -> createStock.execute(quote));
    }
}