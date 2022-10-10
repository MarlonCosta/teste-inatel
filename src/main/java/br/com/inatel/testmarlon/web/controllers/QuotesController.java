package br.com.inatel.testmarlon.web.controllers;

import br.com.inatel.testmarlon.TesteInatelApplication;
import br.com.inatel.testmarlon.core.entities.Quote;
import br.com.inatel.testmarlon.core.usecases.boundaries.ReadAllStocksBoundary;
import br.com.inatel.testmarlon.core.usecases.boundaries.CreateStockBoundary;
import br.com.inatel.testmarlon.core.usecases.boundaries.ReadStockByStockIdBoundary;
import br.com.inatel.testmarlon.web.controllers.models.QuoteRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/quotations")
@RequiredArgsConstructor
public class QuotesController {

    private static final Logger logger = LoggerFactory.getLogger(TesteInatelApplication.class);

    private final CreateStockBoundary createStockBoundary;
    private final ReadAllStocksBoundary readAllStocks;
    private final ReadStockByStockIdBoundary readStockByStockId;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Gets All Quotes, filtered by stockId if provided.")
    @ApiResponse(responseCode = "200", description = "Quotes retrieved successfully")
    public ResponseEntity<List<Quote>> getQuoteByStockId(@RequestParam Optional<String> stockId) {
        if (stockId.isPresent() && !stockId.get().isEmpty()) {
            logger.info(String.format("Searching quotations with stockId = %s", stockId));
            return ResponseEntity.ok(readStockByStockId.execute(stockId.get()));
        }
        logger.info(String.format("Getting all quotations = %s", stockId));
        return ResponseEntity.ok(readAllStocks.execute());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Creates a new quote")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Quote successfully created"),
            @ApiResponse(responseCode = "404", description = "StockId Nonexistent")})
    public ResponseEntity<Quote> createQuote(@RequestBody QuoteRequest request) {
        logger.info(String.format("Creating quote: %s", request));
        return new ResponseEntity<>(createStockBoundary.execute(request.toEntity()), HttpStatus.CREATED);
    }
}
