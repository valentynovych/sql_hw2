package hibernate.service;

import bdConnection.SessionUtil;
import hibernate.dao.UserDAO;
import hibernate.entity.User;
import jakarta.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class UserService extends SessionUtil implements UserDAO {

    Logger logger = LogManager.getLogger(UserService.class);

    @Override
    public void addUser(User user) {

        try (Session session = openTransactionSession()) {

            session.persist(user);

            getTransaction().commit();
            logger.info(user + " has be saved to DB");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error from saved user" + e);
        }
    }

    @Override
    public User getUserById(Long userId) {
        User user = null;

        try (Session session = openTransactionSession()) {

            user = session.get(User.class, userId);

            session.getTransaction().commit();
            logger.info(" user + has been getting to DB");
        } catch (HibernateException e) {
            e.printStackTrace();
            logger.error("Error from get user");
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();

        try (Session session = openTransactionSession()) {

            usersList = session.createQuery("FROM User").list();
            session.getTransaction().commit();
            logger.info("Success getting all users from DB");
        } catch (HibernateException e) {
            logger.error("Error getting all users from DB");
            e.printStackTrace();
        }
        return usersList;
    }

    @Override
    public void updateUserById(User user) {

        try (Session session = openTransactionSession()) {
            session.merge(user);
            session.getTransaction().commit();
            logger.info("Success update user whit Id: " + user.getUserId());
        } catch (HibernateException e) {
            logger.error("Error update " + user);
            e.printStackTrace();
        }

    }

    @Override
    public void deleteUserById(User user) {
        try (Session session = openTransactionSession()) {
            User user1 = getUserById(user.getUserId());
            session.delete(user1);
            session.getTransaction().commit();
            logger.info("Success delete user whit Id: " + user.getUserId());
        } catch (HibernateException e) {
            logger.error("Error delete " + user);
            e.printStackTrace();
        }
    }
}
