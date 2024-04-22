package microservice.registration.restclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "INVENTORYSERVICE")
public interface InventoryFeignClient {

    @GetMapping("/inventory/health")
    String getInventoryHealth();
}
