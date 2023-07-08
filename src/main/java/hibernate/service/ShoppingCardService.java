package hibernate.service;

import bdConnection.SessionUtil;
import hibernate.dao.ShoppingCardDAO;
import hibernate.entity.Product;
import hibernate.entity.ShoppingCard;
import jakarta.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCardService extends SessionUtil implements ShoppingCardDAO {

    Logger logger = LogManager.getLogger(ShoppingCard.class);

    @Override
    public void addProductToCard(ShoppingCard shoppingCard) {
        try (Session session = openTransactionSession()) {

            session.save(shoppingCard);

            session.getTransaction().commit();

            logger.info(shoppingCard + " has been saved to DB");

        } catch (HibernateException e) {

            e.printStackTrace();
            logger.info("Error saved " + shoppingCard);
        }
    }

    @Override
    public void deleteProductById(Long cardId, Long productId) {

        String sql = "DELETE FROM shopping_cart WHERE card_id =:card_id AND product_id =:productId";
        try (Session session = openTransactionSession()) {

            Query query = session.createNativeQuery(sql).addEntity(ShoppingCard.class);
            query.setParameter("card_id", cardId)
                    .setParameter("productId", productId);
            query.executeUpdate();
            session.getTransaction().commit();

            logger.info("Deleted product id: " + productId
                    + " with shoppingCard id: " + cardId
                    + " has been saved to DB");

        } catch (HibernateException e) {

            e.printStackTrace();
            logger.info("Error deleted product id: " + productId
                    + " with shoppingCard id: " + cardId
                    + " has been saved to DB");
        }
    }

    @Override
    public List<Product> getAllProducts(Long cardId) {
        List<Product> productsId = new ArrayList<>();
        try (Session session = openTransactionSession()) {

            productsId = session.createQuery("FROM Product WHERE productId IN (SELECT productId FROM ShoppingCard WHERE cardId =:id)", Product.class)
                    .setParameter("id", cardId).list();
            session.getTransaction().commit();

            logger.info("Product list has been getting to DB");

        } catch (HibernateException e) {

            e.printStackTrace();
            logger.info("Error getting product on card_id:  " + cardId);
        }
        return productsId;
    }

    @Override
    public void clearShoppingCardById(Long cardId) {
        try (Session session = openTransactionSession()) {

            String sql = "DELETE FROM shopping_cart WHERE card_id =:id";
            session.createNativeQuery(sql)
                    .addEntity(ShoppingCard.class)
                    .setParameter("id", cardId).executeUpdate();

            session.getTransaction().commit();

            logger.info("Product list has been deleted on card_id: " + cardId);

        } catch (HibernateException e) {

            e.printStackTrace();
            logger.info("Error clean shopping cart on card_id:  " + cardId);
        }

    }
}
