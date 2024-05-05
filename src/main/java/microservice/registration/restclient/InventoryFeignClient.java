package microservice.registration.restclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "INVENTORYSERVICE")
public interface InventoryFeignClient {

    @GetMapping("/inventory/health")
    String getInventoryHealth();

    @GetMapping("/inventory/ispresent/{id}/{quantity}")
    boolean isInventoryAvailable(@PathVariable("id") String id, @PathVariable("quantity") Integer quantity);

}
