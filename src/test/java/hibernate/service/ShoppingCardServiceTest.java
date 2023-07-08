package hibernate.service;

import hibernate.entity.Product;
import hibernate.entity.ShoppingCard;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ShoppingCardServiceTest extends ShoppingCardService {
    private static ShoppingCard shoppingCard;
    private static Product product;
    private static ProductService productService = new ProductService();

    @BeforeAll
    static void prepareTests() {
        shoppingCard = new ShoppingCard();
        product = productService.getProductById(1L);
        shoppingCard.setProductId(product);
        shoppingCard.setCardId(1L);
    }

    @Test
    @Order(1)
    void testAddProductToCard() {
        addProductToCard(shoppingCard);
        shoppingCard.setProductId(productService.getProductById(2L));
        addProductToCard(shoppingCard);
        shoppingCard.setProductId(productService.getProductById(3L));
        addProductToCard(shoppingCard);
        shoppingCard.setProductId(product);
        List<Product> products = getAllProducts(shoppingCard.getCardId());

        boolean condition = false;
        for (Product product1 : products) {
            if (product1.getProductPrice().equals(product.getProductPrice())
                    && product1.getProductName().equals(product.getProductName())) {
                condition = true;
            }
        }
        assertTrue(condition);
    }

    @Test
    @Order(3)
    void testDeleteProductById() {
        deleteProductById(shoppingCard.getCardId(), shoppingCard.getProductId().getProductId());
        List<Product> products = getAllProducts(shoppingCard.getCardId());
        boolean condition = false;
        for (Product product1 : products) {
            if (product1.getProductPrice().equals(product.getProductPrice())
                    && product1.getProductName().equals(product.getProductName())) {
                condition = true;
            }
        }
        assertFalse(condition);
    }

    @Test
    @Order(2)
    void testGetAllProducts() {
        List<Product> products = getAllProducts(shoppingCard.getCardId());
        assertFalse(products.isEmpty());
    }

    @Test
    @Order(4)
    void testClearShoppingCardById() {
        clearShoppingCardById(shoppingCard.getCardId());
        List<Product> products = getAllProducts(shoppingCard.getCardId());
        assertTrue(products.isEmpty());
    }
}