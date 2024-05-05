package microservice.registration.repository;

import microservice.registration.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<Orders,String> {
}
