package microservice.registration.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import microservice.registration.model.Orders;
import microservice.registration.repository.OrderRepository;
import microservice.registration.restclient.InventoryFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private InventoryFeignClient inventoryFeignClient;

    @Autowired
    private OrderRepository repository;

    @CircuitBreaker(name = "inventoryHealth", fallbackMethod = "fallbackHealth")
    public String getInventoryHealth() {
        return inventoryFeignClient.getInventoryHealth();
    }

    public String fallbackHealth(Throwable  t) {
        return "FallBack Response Service down error :"+ t.getMessage();
    }


    public String bookOrder(Orders order) {

      boolean isPresent =  inventoryFeignClient.isInventoryAvailable(order.getProd_id(),order.getQuantity());
      if (isPresent){
          order.setStatus("Booked");
        repository.save(order);
        return  "order booked";
      }
      return "product not available";
    }
}
