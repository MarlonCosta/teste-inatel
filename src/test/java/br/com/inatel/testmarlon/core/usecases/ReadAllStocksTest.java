package br.com.inatel.testmarlon.core.usecases;

import br.com.inatel.testmarlon.core.entities.Quote;
import br.com.inatel.testmarlon.core.entities.mockrepositories.MockQuoteRepository;
import br.com.inatel.testmarlon.core.entities.repositories.QuoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ReadAllStocksTest {

    private final QuoteRepository quoteRepository = new MockQuoteRepository();
    private final ReadAllStocks readAllStocks = new ReadAllStocks(quoteRepository);
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
    void shouldContainQuote() {
        List<Quote> result = readAllStocks.execute();
        assertNotNull(result);
        assertTrue(result.contains(quote));
    }

    @Test
    void shouldContainMultipleQuotes() {
        quoteRepository.createQuote(quote);
        quoteRepository.createQuote(quote);

        List<Quote> result = readAllStocks.execute();
        assertNotNull(result);
        assertTrue(result.contains(quote));
        assertEquals(3, result.size());
    }
}