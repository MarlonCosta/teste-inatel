package br.com.inatel.testmarlon.persistance;

import br.com.inatel.testmarlon.persistance.entities.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaQuoteRepository extends JpaRepository<QuoteEntity, UUID> {
    List<QuoteEntity> findByStockId(String stockId);
}
