package microservice.registration.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import microservice.registration.restclient.InventoryFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.resilience4j.retry.annotation.Retry;
import java.util.concurrent.TimeoutException;

@Service
public class OrderService {

    @Autowired
    private InventoryFeignClient inventoryFeignClient;

    @CircuitBreaker(name = "inventoryHealth", fallbackMethod = "fallbackHealth")
    @Retry(name = "inventoryRetry")
    @RateLimiter(name = "inventoryRateLimiter")
    public String getInventoryHealth() {
        return inventoryFeignClient.getInventoryHealth();
    }

    public String fallbackHealth(Throwable  t) {
        return "FallBack Response Service down error :"+ t.getMessage();
    }


    }
