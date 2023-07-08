package hibernate.service;

import hibernate.entity.UserDetails;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserDetailsServiceTest extends UserDetailsService {

    static UserDetails userDetails;
    static Session session;
    static Long id;
    @BeforeAll
    static void prepareTests(){
        userDetails = new UserDetails();
        userDetails.setLastName("Last");
        userDetails.setAge(30);
        userDetails.setEmail("Test E-Mail");
        userDetails.setCity("Test city");
    }
    @Test
    @Order(1)
    void testAddUsersDetails() {
        addUsersDetails(userDetails);
        session = openTransactionSession();
        Query query = session.createNativeQuery("SELECT MAX(details_id) FROM users_details");
        id = (Long) query.getSingleResult();
        getTransaction().commit();
        userDetails.setDetailsId(id);
        UserDetails getDetails = getUsersDetailById(userDetails);

        assertEquals(userDetails.getDetailsId(), getDetails.getDetailsId());
        assertEquals(userDetails.getAge(), getDetails.getAge());
        assertEquals(userDetails.getCity(), getDetails.getCity());
    }

    @Test
    @Order(2)
    void testGetUsersDetailById() {
        UserDetails getDetails = getUsersDetailById(userDetails);

        assertEquals(userDetails.getDetailsId(), getDetails.getDetailsId());
        assertEquals(userDetails.getAge(), getDetails.getAge());
        assertEquals(userDetails.getCity(), getDetails.getCity());
    }

    @Test
    @Order(3)
    void testGetAllUsersDetails() {
        List<UserDetails> detailsList = getAllUsersDetails();
        assertFalse(detailsList.isEmpty());
    }

    @Test
    @Order(4)
    void testUpdateUsersDetailsById() {
        UserDetails getDetails = getUsersDetailById(userDetails);
        getDetails.setCity("City2");
        getDetails.setAge(32);
        getDetails.setEmail("mails");
        updateUsersDetailsById(getDetails);

        UserDetails updated = getUsersDetailById(userDetails);

        assertEquals(getDetails.getDetailsId(), updated.getDetailsId());
        assertEquals(getDetails.getAge(), updated.getAge());
        assertEquals(getDetails.getCity(), updated.getCity());
    }

    @Test
    @Order(5)
    void testDeleteUserDetailsById() {
        userDetails.setDetailsId(id);
        deleteUserDetailsById(userDetails);
        assertNull(getUsersDetailById(userDetails));
    }
}