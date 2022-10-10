package br.com.inatel.testmarlon.core.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Locale;

@Data
@NoArgsConstructor
public class ExistingStock implements Serializable {
    private String id;
    private String description;

    public ExistingStock(String id, String description) {
        this.id = id.toLowerCase(Locale.ROOT);
        this.description = description;
    }
}
