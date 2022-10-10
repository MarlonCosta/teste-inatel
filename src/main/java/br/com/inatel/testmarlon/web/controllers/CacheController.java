package br.com.inatel.testmarlon.web.controllers;

import br.com.inatel.testmarlon.TesteInatelApplication;
import br.com.inatel.testmarlon.core.usecases.boundaries.ExistingStocksRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stockcache")
@RequiredArgsConstructor
public class CacheController {

    private static final Logger logger = LoggerFactory.getLogger(TesteInatelApplication.class);
    private final ExistingStocksRepository existingStocksRepository;

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> cleanCache(){
        logger.info("Cleaning cache due to notification from stock-manager");
        existingStocksRepository.cleanCache();
        return ResponseEntity.noContent().build();
    }

}
