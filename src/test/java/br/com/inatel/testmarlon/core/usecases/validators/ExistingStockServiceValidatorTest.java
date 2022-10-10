package br.com.inatel.testmarlon.core.usecases.validators;

import br.com.inatel.testmarlon.core.errors.NonexistentStockException;
import br.com.inatel.testmarlon.core.usecases.boundaries.ExistingStocksRepository;
import br.com.inatel.testmarlon.core.entities.mockrepositories.MockExistingStocksRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExistingStockServiceValidatorTest {

    private final ExistingStocksRepository existingStocksRepository = new MockExistingStocksRepository();
    private final ExistingStockServiceValidator stockServiceValidator = new ExistingStockServiceValidator(existingStocksRepository);

    @BeforeEach
    void setUp() {
        existingStocksRepository.getExistingStocks();
    }

    @Test
    void shouldNotThrowExceptionWhenValidaitngTest() {
        assertDoesNotThrow(() -> stockServiceValidator.validate("VALE3"));
    }

    @Test
    void shouldThrowBusinessExceptionWhenStockIsntFound() {
        assertThrows(NonexistentStockException.class, () -> stockServiceValidator.validate("abc"));
    }
}