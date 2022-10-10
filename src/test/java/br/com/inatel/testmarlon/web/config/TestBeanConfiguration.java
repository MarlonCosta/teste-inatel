package br.com.inatel.testmarlon.web.config;

import br.com.inatel.testmarlon.core.entities.repositories.QuoteRepository;
import br.com.inatel.testmarlon.core.usecases.CreateStock;
import br.com.inatel.testmarlon.core.usecases.ReadAllStocks;
import br.com.inatel.testmarlon.core.usecases.ReadStockByStockId;
import br.com.inatel.testmarlon.core.usecases.boundaries.ExistingStocksRepository;
import br.com.inatel.testmarlon.core.usecases.boundaries.QuoteValidator;
import br.com.inatel.testmarlon.core.usecases.validators.ExistingStockServiceValidator;
import lombok.AllArgsConstructor;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.util.List;

@TestConfiguration
@AllArgsConstructor
public class TestBeanConfiguration {

    @MockBean
    private final QuoteRepository quoteRepository;

    @MockBean
    private final ExistingStocksRepository existingStocksRepository;

    @Bean
    public CreateStock createStock() {
        return new CreateStock(quoteRepository, quoteValidators());
    }

    @Bean
    public ReadAllStocks readAllStocks() {
        return new ReadAllStocks(quoteRepository);
    }

    @Bean
    public ReadStockByStockId readStockByStockId() {
        return new ReadStockByStockId(quoteRepository);
    }

    @Bean
    public List<QuoteValidator> quoteValidators(){
        return List.of(
                new ExistingStockServiceValidator(existingStocksRepository)
        );
    }
}
