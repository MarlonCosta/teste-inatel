package br.com.inatel.testmarlon.core.usecases.boundaries;

import br.com.inatel.testmarlon.core.entities.ExistingStock;

import java.util.List;

public interface ExistingStocksRepository {

    List<ExistingStock> getExistingStocks();
    void cleanCache();

}
