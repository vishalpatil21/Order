package microservice.registration.controller;


import microservice.registration.model.Orders;
import microservice.registration.restclient.InventoryFeignClient;
import microservice.registration.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/health")
    public String getStatus() {
        return "Order Service is up & running!";
    }

    @GetMapping("/inventory/health")
    public String getInventoryHealth() {
        return orderService.getInventoryHealth();
    }


    @PostMapping("/book")
    public String bookOrder(@RequestBody Orders order){
        return orderService.bookOrder(order);
    }
}
