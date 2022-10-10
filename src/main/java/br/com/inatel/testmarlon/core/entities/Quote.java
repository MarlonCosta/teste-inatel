package br.com.inatel.testmarlon.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Quote {
    private UUID id;
    private String stockId;
    private Map<LocalDate, Integer> quotes;
}
