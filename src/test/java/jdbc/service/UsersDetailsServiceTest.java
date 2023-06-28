package jdbc.service;

import jdbc.entity.UsersDetails;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UsersDetailsServiceTest extends UsersDetailsService {

    private static UsersDetails usersDetails;
    @BeforeAll
    static void createUserDetails(){
        usersDetails = new UsersDetails();
        usersDetails.setDetailsId(100L);
        usersDetails.setLastName("Test Name");
        usersDetails.setAge(100);
        usersDetails.setEmail("Test E-Mail");
        usersDetails.setCity("Test City");
    }

    @Test
    @Order(1)
    void testAddUsersDetails() {
        addUsersDetails(usersDetails);
        UsersDetails getUserDetails = getUsersDetailById(usersDetails.getDetailsId());
        assertEquals(usersDetails, getUserDetails, "User Details must bu equals");
    }

    @Test
    @Order(2)
    void testGetUsersDetailById() {
        UsersDetails getUserDetails = getUsersDetailById(usersDetails.getDetailsId());
        assertEquals(usersDetails, getUserDetails, "User Details must bu equals");
    }

    @Test
    @Order(3)
    void testGetAllUsersDetails() {
        List<UsersDetails> usersDetailsList = getAllUsersDetails();
        assertFalse(usersDetailsList.isEmpty());
    }

    @Test
    @Order(4)
    void testUpdateUsersDetailsById() {
        UsersDetails newUsersDetails = new UsersDetails();
        newUsersDetails.setDetailsId(usersDetails.getDetailsId());
        newUsersDetails.setLastName("Test Name 2");
        newUsersDetails.setAge(101);
        newUsersDetails.setEmail("Test E-Mail 2");
        newUsersDetails.setCity("Test City 2");
        updateUsersDetailsById(newUsersDetails);
        UsersDetails getDetails = getUsersDetailById(newUsersDetails.getDetailsId());
        assertEquals(newUsersDetails, getDetails, "User Details must bu equals");
    }

    @Test
    @Order(5)
    void testDeleteUserDetailsById() {
        deleteUserDetailsById(usersDetails.getDetailsId());
        UsersDetails getDetails = getUsersDetailById(usersDetails.getDetailsId());
        assertNull(getDetails, "User Details must be null");
    }
}