package hibernate.service;

import bdConnection.SessionUtil;
import hibernate.dao.OrderDAO;
import hibernate.entity.Order;
import hibernate.entity.Product;
import hibernate.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderService extends SessionUtil implements OrderDAO {

    Logger logger = LogManager.getLogger(OrderService.class);

    public Order createOrder(User user) {
        Order order = new Order();
        ShoppingCardService cardService = new ShoppingCardService();
        UserService userService = new UserService();
        List<Product> products = cardService.getAllProducts(user.getUserId());

        Double sumPrice = 0.0;
        for (Product product : products) {
            sumPrice += product.getProductPrice();
        }

        order.setListProducts(products);
        order.setOrderPrice(sumPrice);
        order.setUserId(user);
        order.setOrderDate(LocalDateTime.now());

        user.setOrdersCount(user.getOrdersCount() + 1);

        userService.updateUserById(user);
        //cardService.clearShoppingCardById(user.getUserId());

        logger.info("Order has been created");
        saveOrder(order);
        return order;
    }

    @Override
    public void saveOrder(Order order) {

        try (Session session = openTransactionSession()) {

            session.persist(order);
            getTransaction().commit();

            logger.info(" order +  has been saved to DB");

        } catch (HibernateException e) {

            e.printStackTrace();
            logger.info("Error saved " + order);
        }
    }

    @Override
    public List<Order> getAllUserOrdersById(Long userId) {
        List<Order> orderList = new ArrayList<>();
        UserService userService = new UserService();
        User user = userService.getUserById(userId);
        try (Session session = openTransactionSession()) {

            orderList = session.createQuery("FROM Order WHERE userId =: id", Order.class)
                    .setParameter("id", user).list();
            getTransaction().commit();

            logger.info("List orders has been get from DB");


        } catch (HibernateException e) {

            e.printStackTrace();
            logger.info("Error getting list orders by id: " + userId);
        }
        return orderList;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orderList = new ArrayList<>();
        try (Session session = openTransactionSession()) {

            orderList = session.createQuery("FROM Order", Order.class).list();
            getTransaction().commit();

            logger.info("List all orders has been get from DB");

        } catch (HibernateException e) {

            e.printStackTrace();
            logger.info("Error getting list all orders ");
        }
        return orderList;
    }
}
