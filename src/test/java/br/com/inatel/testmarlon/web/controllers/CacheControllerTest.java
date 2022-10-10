package br.com.inatel.testmarlon.web.controllers;

import br.com.inatel.testmarlon.core.usecases.boundaries.ExistingStocksRepository;
import br.com.inatel.testmarlon.web.config.TestCacheConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebFluxTest(CacheController.class)
@Import({CacheController.class, TestCacheConfiguration.class})
@EnableCaching
class CacheControllerTest {

    @Autowired
    ExistingStocksRepository existingStocksRepository;

    @Autowired
    CacheManager cacheManager;

    @Test
    void testCache() {
        assertEquals(0, cacheManager.getCacheNames().size());
        existingStocksRepository.getExistingStocks();
        assertEquals(1, cacheManager.getCacheNames().size());
    }
}