package jdbc.service;

import jdbc.entity.Orders;
import jdbc.entity.Products;
import jdbc.entity.ShoppingCard;
import jdbc.entity.Users;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrdersServiceTest extends OrdersService {


    @Test
    @Order(4)
    void testCreateOrder() {

        ShoppingCardService cardService = new ShoppingCardService();
        ProductsService productsService = new ProductsService();
        UsersService usersService = new UsersService();

        Users user = new Users();
        Long id = 100L;
        user.setDetailsId(id);
        user.setUserId(id);
        user.setFirstName("Test User");
        user.setOrdersCount(id.intValue());
        user.setCardId(id);
        usersService.addUser(user);

        ShoppingCard shoppingCard = new ShoppingCard();
        shoppingCard.setCardId(user.getCardId());
        shoppingCard.setProduct_id(1L);
        cardService.addProductToCard(shoppingCard);
        shoppingCard.setProduct_id(2L);
        cardService.addProductToCard(shoppingCard);
        shoppingCard.setProduct_id(3L);
        cardService.addProductToCard(shoppingCard);

        Orders orders = createOrder(user);

        List<Orders> ordersList = getAllUserOrdersById(user.getUserId());
        assertTrue(ordersList.contains(orders));

        Products product1 = productsService.getProductById(1L);
        Products product2 = productsService.getProductById(2L);
        Products product3 = productsService.getProductById(3L);

        Double sumProductPrice = product1.getProductPrice()
                + product2.getProductPrice()
                + product3.getProductPrice();
        String listProducts = product1.getProductName() + ", "
                + product2.getProductName() + ", "
                + product3.getProductName();

        String deleteOrders = "DELETE FROM orders WHERE order_id = ?";
        PreparedStatement statement = null;
        connection = getConnection();
        try {
            statement = connection.prepareStatement(deleteOrders);
            statement.setLong(1, 100);
            statement.executeUpdate();
            statement.setLong(1, 101);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatementAndConnection(statement, connection);
        }

        usersService.deleteUserById(user.getUserId());
        boolean equalsPrice = orders.getOrderPrice().equals(sumProductPrice);
        assertTrue(equalsPrice);
        assertTrue(orders.getListProducts().equals(listProducts));

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
        assertTrue(ordersList.contains(order));
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