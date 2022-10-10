package br.com.inatel.testmarlon.web.controllers.models;

import br.com.inatel.testmarlon.core.entities.Quote;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Data
@AllArgsConstructor
public class QuoteRequest {
    private UUID id;
    @Schema(required = true) private String stockId;
    @Schema(example = "\"28-02-1993\":29", implementation = HashMap.class)
    private HashMap<LocalDate, Integer> quotes;

    public Quote toEntity() {
        return new Quote(
                Optional.ofNullable(id).orElseGet(UUID::randomUUID),
                stockId,
                Optional.ofNullable(quotes).orElseGet(HashMap::new)
        );
    }
}
