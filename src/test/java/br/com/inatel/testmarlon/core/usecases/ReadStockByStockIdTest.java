package br.com.inatel.testmarlon.core.usecases;

import br.com.inatel.testmarlon.core.entities.Quote;
import br.com.inatel.testmarlon.core.entities.repositories.QuoteRepository;
import br.com.inatel.testmarlon.core.entities.mockrepositories.MockQuoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ReadStockByStockIdTest {

    private final QuoteRepository quoteRepository = new MockQuoteRepository();
    private final ReadStockByStockId readStockByStockId = new ReadStockByStockId(quoteRepository);
    private Quote quote;

    @BeforeEach
    void setUp() {
        quote = new Quote(
                UUID.randomUUID(),
                "VALE3",
                new HashMap<>()
        );
        quoteRepository.createQuote(quote);
    }

    @Test
    void shouldSucceedSinceQuoteExists() {
        assertTrue(readStockByStockId.execute("VALE3").contains(quote));
    }

    @Test
    void shouldGetEmptyList() {
        assertTrue(readStockByStockId.execute("abc").isEmpty());
    }
}