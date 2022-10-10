package br.com.inatel.testmarlon.persistance;

import br.com.inatel.testmarlon.core.entities.ExistingStock;
import br.com.inatel.testmarlon.core.usecases.boundaries.ExistingStocksRepository;
import br.com.inatel.testmarlon.persistance.entities.NotificationRegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ExistingStockRepositoryImpl implements ExistingStocksRepository {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${stockmanager.stocks.uri:http://localhost:8080}")
    private String stockManagerUri;

    @Value("${quotemanager.host:localhost}")
    private String quoteManagerHost;

    @Value("${server.port:8081}")
    private Integer quoteManagerPort;

    @Override
    @Cacheable("existing_stocks")
    public List<ExistingStock> getExistingStocks() {
        return Arrays.stream(Objects.requireNonNull(
                restTemplate.getForObject(stockManagerUri + "/stock", ExistingStock[].class))).collect(Collectors.toList());
    }

    @CacheEvict("existing_stocks")
    public void cleanCache() {
    }

    @PostConstruct
    public void registerForNotification() {
        NotificationRegistrationRequest request = new NotificationRegistrationRequest(quoteManagerHost, quoteManagerPort);
        restTemplate.postForEntity(stockManagerUri + "/notification", request, String.class);
    }
}
