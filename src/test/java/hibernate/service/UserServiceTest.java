package hibernate.service;

import hibernate.entity.Product;
import hibernate.entity.User;
import hibernate.entity.UserDetails;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest extends UserService {

    static User user;
    static List<Product> products;
    static Long id;
    static Session session;

    @BeforeAll
    static void prepareTest() {
        products = new ArrayList<>();
        List<hibernate.entity.Order> orders = new ArrayList<>();
        UserDetails userDetails = new UserDetails();
        userDetails.setLastName("Last");
        userDetails.setCity("Test city");
        userDetails.setEmail("Test email");
        userDetails.setAge(45);
        user = new User();
        user.setDetailsId(userDetails);
        user.setOrdersCount(0);
        user.setFirstName("Firs");
        user.setProducts(products);
        user.setOrders(orders);
    }

    @Test
    @Order(1)
    void testAddUser() {

        addUser(user);
        session = openTransactionSession();
        Query query = session.createNativeQuery("SELECT MAX(user_id) FROM users");
        id = (Long) query.getSingleResult();

        User getUser = getUserById(id);
        assertEquals(user.getFirstName(), getUser.getFirstName());

    }

    @Test
    @Order(2)
    void testGetUserById() {
        User getUser = getUserById(id);
        assertEquals(user.getFirstName(), getUser.getFirstName());
        assertEquals(user.getOrdersCount(), getUser.getOrdersCount());
    }

    @Test
    @Order(3)
    void testGetAllUsers() {
        List<User> users = getAllUsers();
        assertFalse(users.isEmpty());
    }

    @Test
    @Order(4)
    void testUpdateUserById() {
        User getUser = getUserById(id);
        getUser.setOrdersCount(5);
        products.add(new Product());
        getUser.setProducts(products);
        updateUserById(getUser);
        User updatedUser = getUserById(id);

        assertEquals(getUser.getUserId(), updatedUser.getUserId());
        assertEquals(getUser.getProducts().get(0).getProductName(),
                updatedUser.getProducts().get(0).getProductName());
        assertEquals(getUser.getFirstName(), updatedUser.getFirstName());
    }

    @Test
    @Order(5)
    void testDeleteUserById() {
        user.setUserId(id);
        deleteUserById(user);
        assertNull(getUserById(id));

    }
}