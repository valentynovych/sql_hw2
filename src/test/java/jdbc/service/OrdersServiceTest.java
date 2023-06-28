package jdbc.service;

import jdbc.entity.Orders;
import org.junit.jupiter.api.*;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrdersServiceTest extends OrdersService {



    @Test
    void testCreateOrder() {
    }

    @Test
    @Order(1)
    void testSaveOrder() {
        Orders order = new Orders();
        Long id = 100L;
        order.setOrderId(id);
        order.setUserId(10L);
        order.setOrderPrice(id.doubleValue());
        Long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        order.setOrderDate(Date.valueOf(date.toLocalDate()));
        order.setListProducts("Product1, Product2, Product3");

        saveOrder(order);

        List<Orders> ordersList = getAllUserOrdersById(order.getUserId());
        assertTrue(ordersList.add(order));
    }

    @Test
    @Order(2)
    void testGetAllUserOrdersById() {
        List<Orders> ordersList = getAllUserOrdersById(10L);
        assertFalse(ordersList.isEmpty());
    }

    @Test
    @Order(3)
    void testGetAllOrders() {
        List<Orders> ordersList = getAllOrders();
        assertFalse(ordersList.isEmpty());
    }
}