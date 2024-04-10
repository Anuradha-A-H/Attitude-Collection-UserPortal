package Attitude_Collection.AttitudeCollection.repository;

import Attitude_Collection.AttitudeCollection.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
