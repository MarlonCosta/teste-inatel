package br.com.inatel.testmarlon.persistance.converters;

import br.com.inatel.testmarlon.core.entities.Quote;
import br.com.inatel.testmarlon.persistance.entities.QuoteEntity;
import org.springframework.stereotype.Service;

@Service
public class QuoteConverter {

    public static Quote convertToQuote(QuoteEntity entity) {
        return new Quote(entity.getId(), entity.getStockId(), entity.getQuotes());
    }

    public static QuoteEntity convertToQuoteEntity(Quote quote) {
        return new QuoteEntity(quote.getId(), quote.getStockId(), quote.getQuotes());
    }

}
