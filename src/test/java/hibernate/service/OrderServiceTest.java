package hibernate.service;

import hibernate.entity.Order;
import hibernate.entity.Product;
import hibernate.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.LocalDateTime;
import java.util.List;

;import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderServiceTest extends OrderService {

    static User user;
    static Order newOrder = new Order();
    static UserService userService = new UserService();
    @BeforeAll
    static void prepareTests(){
        user = userService.getUserById(1L);

        Double sumPrice = 0.0;
        for (Product product : user.getProducts()){
            sumPrice += product.getProductPrice();
        }
        newOrder.setUserId(user);
        newOrder.setOrderDate(LocalDateTime.now());
        newOrder.setListProducts(user.getProducts());
        newOrder.setOrderPrice(sumPrice);
    }
    @Test
    @org.junit.jupiter.api.Order(4)
    void testCreateOrder() {
        Order order = createOrder(user);
        assertNotNull(order);
        boolean contains = false;
        List<Order> allUserOrdersById = getAllUserOrdersById(user.getUserId());
        for (Order order1 : allUserOrdersById){
            if (order1.getListProducts().equals(newOrder.getListProducts())
                    && order1.getOrderPrice().equals(newOrder.getOrderPrice()));
            contains = true;
        }
        assertTrue(contains);
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    void testSaveOrder() {
        saveOrder(newOrder);

        boolean contains = false;
        List<Order> allUserOrdersById = getAllUserOrdersById(user.getUserId());
        for (Order order1 : allUserOrdersById){
            if (order1.getListProducts().equals(newOrder.getListProducts())
                    && order1.getOrderPrice().equals(newOrder.getOrderPrice()));
            contains = true;
        }
        assertTrue(contains);
    }

    @Test @org.junit.jupiter.api.Order(2)
    void testGetAllUserOrdersById() {
        List<Order> ordersList = getAllUserOrdersById(1L);
        assertFalse(ordersList.isEmpty());
    }

    @Test @org.junit.jupiter.api.Order(3)
    void testGetAllOrders() {
        List<Order> ordersList = getAllOrders();
        assertFalse(ordersList.isEmpty());
    }
}