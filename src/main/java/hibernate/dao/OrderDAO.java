package hibernate.dao;

import hibernate.entity.Order;

import java.util.List;

public interface OrderDAO {
    void saveOrder(Order orders);

    List<Order> getAllUserOrdersById(Long userId);

    List<Order> getAllOrders();
}
