package hibernate.service;

import bdConnection.SessionUtil;
import hibernate.dao.ProductDAO;
import hibernate.entity.Product;
import jakarta.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class ProductService extends SessionUtil implements ProductDAO {
    Logger logger = LogManager.getLogger(ProductService.class);

    @Override
    public void addProducts(Product product) {

        try (Session session = openTransactionSession()) {

            session.persist(product);
            session.getTransaction().commit();

            logger.info(product + " has been saved to DB");

        } catch (HibernateException e) {

            e.printStackTrace();
            logger.info("Error saved " + product);
        }
    }

    @Override
    public Product getProductById(Long productId) {

        Product product = null;

        try (Session session = openTransactionSession()) {

            product = session.get(Product.class, productId);
            session.getTransaction().commit();
            logger.info("Success get Product, id: " + productId);

        } catch (HibernateException e) {
            logger.error("Error getting: " + product);
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();

        try (Session session = openTransactionSession()) {

            productList = session.createQuery("FROM Product").list();
            session.getTransaction().commit();
            logger.info("Success get list Product");

        } catch (HibernateException e) {
            logger.error("Error getting product list");
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public void updateProductById(Product product) {

        try (Session session = openTransactionSession()) {

            session.merge(product);
            session.getTransaction().commit();
            logger.info(product + " has been update on DB");

        } catch (HibernateException e) {
            e.printStackTrace();
            logger.info("Error updated " + product);
        }

    }

    @Override
    public void deleteProductById(Product product) {

        try (Session session = openTransactionSession()) {
            session.delete(product);
            session.getTransaction().commit();
            logger.info(product + " has been deleted on DB");
        } catch (HibernateException e) {
            e.printStackTrace();
            logger.info("Error deleted " + product);
        }
    }
}
