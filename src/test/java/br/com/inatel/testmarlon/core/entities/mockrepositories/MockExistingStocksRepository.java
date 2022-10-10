package br.com.inatel.testmarlon.core.entities.mockrepositories;

import br.com.inatel.testmarlon.core.entities.ExistingStock;
import br.com.inatel.testmarlon.core.usecases.boundaries.ExistingStocksRepository;

import java.util.ArrayList;
import java.util.List;

public class MockExistingStocksRepository implements ExistingStocksRepository {

    private final List<ExistingStock> stocks = new ArrayList<>();

    @Override
    public List<ExistingStock> getExistingStocks() {

        if (stocks.isEmpty()) {
            ExistingStock stock0 = new ExistingStock("VALE3", "Vale do Rio Doce");
            ExistingStock stock1 = new ExistingStock("MGLU3", "Magazine Luiza");
            stocks.add(stock0);
            stocks.add(stock1);
        }

        return stocks;
    }

    @Override
    public void cleanCache() {
    }
}
