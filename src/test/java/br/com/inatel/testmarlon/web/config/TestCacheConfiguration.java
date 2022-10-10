package br.com.inatel.testmarlon.web.config;

import br.com.inatel.testmarlon.core.usecases.boundaries.ExistingStocksRepository;
import br.com.inatel.testmarlon.persistance.repositories.ExistingStockRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@TestConfiguration
@AllArgsConstructor
public class TestCacheConfiguration {

    @MockBean
    RestTemplate restTemplate;

    @Bean
    public ExistingStocksRepository existingStocksRepository() {
        return new ExistingStockRepositoryImpl();
    }
}
