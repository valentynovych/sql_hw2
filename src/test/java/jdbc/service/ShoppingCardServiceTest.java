package jdbc.service;

import jdbc.entity.Products;
import jdbc.entity.ShoppingCard;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ShoppingCardServiceTest extends ShoppingCardService {
    private static ShoppingCard shoppingCard;
    private static Products products;
    private static ProductsService productsService;

    @BeforeAll
    static void createShoppingCard() {
        shoppingCard = new ShoppingCard();
        Long id = 100L;
        shoppingCard.setProduct_id(id);
        shoppingCard.setCardId(10L);
    }

    @Test
    @Order(1)
    void testAddProductToCard() {
        products = new Products();
        products.setProductId(100L);
        productsService = new ProductsService();
        productsService.addProducts(products);
        addProductToCard(shoppingCard);
        List<Long> shoppingCards = getAllProducts(shoppingCard.getCardId());
        assertTrue(shoppingCards.contains(shoppingCard.getProduct_id()));

    }

    @Test
    @Order(2)
    void testGetAllProducts() {
        List<Long> listProductIds = getAllProducts(shoppingCard.getCardId());
        assertFalse(listProductIds.isEmpty());
    }

    @Test
    @Order(3)
    void testDeleteProductById() {
        deleteProductById(shoppingCard.getCardId(), shoppingCard.getProduct_id());
        List<Long> shoppingCards = getAllProducts(shoppingCard.getCardId());
        assertFalse(shoppingCards.contains(shoppingCard.getProduct_id()));
    }

    @Test
    @Order(4)
    void testClearShoppingCardById() {
        clearShoppingCardById(shoppingCard.getCardId());
        List<Long> listProductIds = getAllProducts(shoppingCard.getCardId());
        assertTrue(listProductIds.isEmpty());
    }
}