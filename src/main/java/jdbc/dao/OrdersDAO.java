package jdbc.dao;

import jdbc.entity.Orders;

import java.util.List;

public interface OrdersDAO {
    void saveOrder(Orders orders);

    List<Orders> getAllUserOrdersById(Long userId);

    List<Orders> getAllOrders();
}
