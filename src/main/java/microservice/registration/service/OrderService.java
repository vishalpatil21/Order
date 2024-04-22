package microservice.registration.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import microservice.registration.restclient.InventoryFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private InventoryFeignClient inventoryFeignClient;

    @CircuitBreaker(name = "inventoryHealth", fallbackMethod = "fallbackHealth")
    public String getInventoryHealth() {
        return inventoryFeignClient.getInventoryHealth();
    }

    public String fallbackHealth(Throwable  t) {
        return "FallBack Response Service down error :"+ t.getMessage();
    }


    }
