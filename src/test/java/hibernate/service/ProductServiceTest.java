package hibernate.service;

import hibernate.entity.Product;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductServiceTest extends ProductService {

    static Session session;
    static Product product;
    static Long id;

    @BeforeAll
    static void prepareTests(){
        product = new Product();
        product.setProductName("Test Product");
        product.setProductPrice(100.10);
        product.setAmount(100);
    }
    @Test
    @Order(1)
    void testAddProducts() {

        addProducts(product);
        session = openTransactionSession();
        Query query = session.createNativeQuery("SELECT MAX(product_id) FROM products");
        id = (Long) query.getSingleResult();
        getTransaction().commit();

        Product addedProduct = getProductById(id);
        assertNotNull(addedProduct);
        assertEquals(product, addedProduct);
    }

    @Test
    @Order(2)
    void testGetProductById() {
        Product addedProduct = getProductById(id);
        assertNotNull(addedProduct);
        assertEquals(product, addedProduct);
    }

    @Test
    @Order(3)
    void testGetAllProducts() {
        List<Product> products = getAllProducts();
        assertFalse(products.isEmpty());
    }

    @Test
    @Order(4)
    void testUpdateProductById() {
        Product updatedProduct = new Product();
        updatedProduct.setProductId(id);
        updatedProduct.setProductName("Updated product");
        updatedProduct.setProductPrice(190.90);
        updatedProduct.setAmount(190);
        updateProductById(updatedProduct);

        Product getProduct = getProductById(id);

        assertEquals(updatedProduct, getProduct);
    }

    @Test
    @Order(5)
    void testDeleteProductById() {
        deleteProductById(product);
        assertNull(getProductById(id));
    }
}