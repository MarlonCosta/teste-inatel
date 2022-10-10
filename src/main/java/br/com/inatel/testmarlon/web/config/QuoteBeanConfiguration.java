package br.com.inatel.testmarlon.web.config;


import br.com.inatel.testmarlon.core.entities.repositories.QuoteRepository;
import br.com.inatel.testmarlon.core.usecases.CreateStock;
import br.com.inatel.testmarlon.core.usecases.ReadAllStocks;
import br.com.inatel.testmarlon.core.usecases.ReadStockByStockId;
import br.com.inatel.testmarlon.core.usecases.boundaries.ExistingStocksRepository;
import br.com.inatel.testmarlon.core.usecases.validators.ExistingStockServiceValidator;
import br.com.inatel.testmarlon.core.usecases.boundaries.QuoteValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class QuoteBeanConfiguration {

    private final QuoteRepository quoteRepository;
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
