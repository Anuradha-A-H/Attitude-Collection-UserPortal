package Attitude_Collection.AttitudeCollection.repository;

import Attitude_Collection.AttitudeCollection.entity.Orders;
import Attitude_Collection.AttitudeCollection.response.ResponseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.stream.Collectors;

public interface OrderRepository extends JpaRepository<Orders, String> {

    @Query("SELECT SUM(o.totalAmount) FROM Orders o WHERE MONTH(o.orderDate) = MONTH(CURRENT_DATE()) AND YEAR(o.orderDate) = YEAR(CURRENT_DATE())")
    Integer getTotalEarningMonthly();
    @Query("SELECT SUM(o.totalAmount) FROM Orders o WHERE YEAR(o.orderDate) = YEAR(CURRENT_DATE())")
    Integer getTotalEarningAnnual();
    @Query("SELECT COUNT(o) FROM Orders o")
    Integer getTotalOrders();

    @Query("SELECT COUNT(o) FROM Orders o WHERE orderstatus = 0")
    Integer getPendingOrders();

    @Query("SELECT o FROM Orders o WHERE o.orderstatus = 0 ORDER BY o.orderDate DESC")
    List<Orders> getRecentOrdersList();

    default List<Orders> getRecent5Orders() {
        return getRecentOrdersList().stream().limit(5).collect(Collectors.toList());
    }
}
