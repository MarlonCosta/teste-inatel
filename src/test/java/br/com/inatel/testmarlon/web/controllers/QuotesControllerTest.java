package br.com.inatel.testmarlon.web.controllers;

import br.com.inatel.testmarlon.core.entities.ExistingStock;
import br.com.inatel.testmarlon.core.entities.Quote;
import br.com.inatel.testmarlon.core.entities.repositories.QuoteRepository;
import br.com.inatel.testmarlon.core.usecases.boundaries.ExistingStocksRepository;
import br.com.inatel.testmarlon.web.config.TestBeanConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@WebFluxTest
@Import({QuotesController.class, TestBeanConfiguration.class})
class QuotesControllerTest {

    @Autowired
    QuoteRepository quoteRepository;

    @Autowired
    ExistingStocksRepository existingStocksRepository;

    @MockBean
    Logger logger;

    @Autowired
    private WebTestClient webClient;

    @BeforeEach
    void setUp() {
        ExistingStock existingStock = new ExistingStock(
                "VALE3",
                "Vale do Rio Doce"
        );
        Mockito.when(existingStocksRepository.getExistingStocks()).thenReturn(List.of(existingStock));
    }

    @Test
    void getQuoteByStockId() {
        Quote quote0 = new Quote(
                UUID.randomUUID(),
                "VALE3",
                new HashMap<>()
        );

        Quote quote1 = new Quote(
                UUID.randomUUID(),
                "VALE3",
                new HashMap<>()
        );

        Mockito.when(quoteRepository.findAllByStockId("vale3")).thenReturn(List.of(quote0, quote1));

        webClient.get()
                .uri("/quotations?stockId=vale3")
                .exchange()
                .expectStatus().is2xxSuccessful().expectBodyList(Quote.class).hasSize(2);
    }

    @Test
    void getQuoteByNonexistentStockIdShouldReturnEmptyList() {
        Quote quote0 = new Quote(
                UUID.randomUUID(),
                "VALE3",
                new HashMap<>()
        );

        Mockito.when(quoteRepository.getAllQuotes()).thenReturn(List.of(quote0));

        webClient.get()
                .uri("/quotations?stockId=None")
                .exchange()
                .expectStatus().is2xxSuccessful().expectBodyList(Quote.class).hasSize(0);
    }

    @Test
    void createQuote() {
        Quote quote = new Quote(
                UUID.randomUUID(),
                "VALE3",
                new HashMap<>()
        );

        Mockito.when(quoteRepository.createQuote(quote)).thenReturn(quote);

        webClient.post()
                .uri("/quotations")
                .body(BodyInserters.fromValue(quote))
                .exchange()
                .expectStatus().isCreated().expectBody(Quote.class).isEqualTo(quote);

        Mockito.verify(quoteRepository, times(1)).createQuote(quote);
    }

    @Test
    void createQuoteWithNonexistentStockId() {
        Quote quote = new Quote(
                UUID.randomUUID(),
                "VALEU",
                new HashMap<>()
        );

        webClient.post()
                .uri("/quotations")
                .body(BodyInserters.fromValue(quote))
                .exchange()
                .expectStatus().isNotFound();
    }
}