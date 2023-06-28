package jdbc.service;

import jdbc.entity.Users;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UsersServiceTest extends UsersService {

    private static Users user;
    UsersDetailsService usersDetailsService = new UsersDetailsService();
    @BeforeAll
    static void createUser(){
        user = new Users();
        Long userId = 100L;
        user.setDetailsId(userId);
        user.setUserId(userId);
        user.setFirstName("Alex");
        user.setOrdersCount(userId.intValue());
        user.setCardId(userId);
    }
    @Test
    @Order(1)
    void testAddUser() {

        addUser(user);
        Users getUser = getUserById(user.getUserId());
        assertEquals(user, getUser, "Add users is correct");
    }

    @Test
    @Order(2)
    void testGetUserById() {
        Users getUser = getUserById(user.getUserId());
        assertEquals(user, getUser, "The received user is correct");
    }

    @Test
    @Order(3)
    void testGetAllUsers() {
        List<Users> usersList = getAllUsers();
        assertFalse(usersList.isEmpty());

    }

    @Test
    @Order(4)
    void testUpdateUserById() {
        Users getUser = getUserById(user.getUserId());
        Long testNumber = 45L;
        getUser.setFirstName("Romio");
        getUser.setOrdersCount(testNumber.intValue());
        getUser.setCardId(testNumber);
        updateUserById(getUser);
        Users newUser = getUserById(user.getUserId());
        assertEquals(getUser, newUser, "Update user is correct");
    }

    @Test
    @Order(5)
    void testDeleteUserById() {
        deleteUserById(user.getUserId());
        usersDetailsService.deleteUserDetailsById(user.getDetailsId());
        Users getUser = getUserById(user.getUserId());
        assertNull(getUser, "User should be null");
    }
}