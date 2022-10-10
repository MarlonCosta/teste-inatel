package br.com.inatel.testmarlon.persistance;

import br.com.inatel.testmarlon.core.entities.Quote;
import br.com.inatel.testmarlon.core.entities.repositories.QuoteRepository;
import br.com.inatel.testmarlon.persistance.converters.QuoteConverter;
import br.com.inatel.testmarlon.persistance.entities.QuoteEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class QuoteRepositoryImpl implements QuoteRepository {

    private final JpaQuoteRepository jpaQuoteRepository;

    public QuoteRepositoryImpl(@Lazy JpaQuoteRepository jpaQuoteRepository) {
        this.jpaQuoteRepository = jpaQuoteRepository;
    }

    @Override
    public List<Quote> getAllQuotes() {
        return jpaQuoteRepository.findAll().stream().map(QuoteConverter::convertToQuote).collect(Collectors.toList());
    }

    @Override
    public List<Quote> findAllByStockId(String stockId) {
        List<QuoteEntity> result = jpaQuoteRepository.findByStockId(stockId);
        return result.stream().map(QuoteConverter::convertToQuote).collect(Collectors.toList());
    }

    @Override
    public Quote createQuote(Quote quoteRequest) {
        jpaQuoteRepository.existsById(quoteRequest.getId());

        QuoteEntity newQuote = jpaQuoteRepository.save(QuoteConverter.convertToQuoteEntity(quoteRequest));

        return QuoteConverter.convertToQuote(newQuote);
    }
}
