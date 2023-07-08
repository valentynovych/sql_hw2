package hibernate.service;

import bdConnection.SessionUtil;
import hibernate.dao.UserDetailsDAO;
import hibernate.entity.UserDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsService extends SessionUtil implements UserDetailsDAO {
    private final Logger logger = LogManager.getLogger(UserDetailsService.class);

    @Override
    public void addUsersDetails(UserDetails usersDetails) {
        try (Session session = openTransactionSession()) {

            session.persist(usersDetails);
            session.getTransaction().commit();
            logger.info(usersDetails + " has be saved to DB");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error from saved userDetails");
        }
    }

    @Override
    public UserDetails getUsersDetailById(UserDetails usersDetails) {
        UserDetails getDetails = null;

        try (Session session = openTransactionSession()) {

            getDetails = session.get(UserDetails.class, usersDetails.getDetailsId());
            session.getTransaction().commit();

            logger.info("Success get userDetails, id: " + usersDetails.getDetailsId());
        } catch (HibernateException e) {
            e.printStackTrace();
            logger.error("Error get userDetails, id: " + usersDetails.getDetailsId());
        }
        return getDetails;
    }

    @Override
    public List<UserDetails> getAllUsersDetails() {
        List<UserDetails> detailsList = new ArrayList<>();
        try (Session session = openTransactionSession()) {

            detailsList = session.createQuery("FROM UserDetails", UserDetails.class).list();
            session.getTransaction().commit();
            logger.info("Success get all userDetails");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error get all userDetails");
        }
        return detailsList;
    }

    @Override
    public void updateUsersDetailsById(UserDetails usersDetails) {
        try (Session session = openTransactionSession()) {

            session.saveOrUpdate(usersDetails);
            session.getTransaction().commit();
            logger.info(usersDetails + " has been updated on DB");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error updated userDetails");
        }
    }

    @Override
    public void deleteUserDetailsById(UserDetails usersDetails) {
        try (Session session = openTransactionSession()) {
            session.delete(usersDetails);
            session.getTransaction().commit();
            logger.info(usersDetails + " has been deleted on DB");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error deleted userDetails");
        }
    }
}


