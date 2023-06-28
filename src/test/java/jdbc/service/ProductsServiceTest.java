package jdbc.service;

import jdbc.entity.Products;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductsServiceTest extends ProductsService {

    @Test
    void testAddProducts() {
        Products product = new Products();
        product.setProductId(100L);
        product.setProductName("Test Product");
        product.setProductPrice(100.10);
        product.setAmount(100);

        addProducts(product);

        Products addedProduct = getProductById(100L);
        deleteProductById(product.getProductId());
        assertNotNull(addedProduct);
        assertEquals(product, addedProduct);
    }

    @Test
    void testGetProductById() {
        Products product = new Products();
        product.setProductId(100L);
        product.setProductName("Test Product");
        product.setProductPrice(100.10);
        product.setAmount(100);

        addProducts(product);

        Products getProduct = getProductById(product.getProductId());
        deleteProductById(product.getProductId());

        assertEquals(product, getProduct);
    }

    @Test
    void testGetAllProducts() {
        List<Products> productsList = getAllProducts();
        assertFalse(productsList.isEmpty());
    }

    @Test
    void testUpdateProductById() {
        Products product = new Products();
        product.setProductId(100L);
        product.setProductName("Test Product");
        product.setProductPrice(100.10);
        product.setAmount(100);

        addProducts(product);

        Products updatedProduct = new Products();
        updatedProduct.setProductId(product.getProductId());
        updatedProduct.setProductName("Updated product");
        updatedProduct.setProductPrice(190.90);
        updatedProduct.setAmount(190);
        updateProductById(product.getProductId(), updatedProduct);

        Products getProduct = getProductById(product.getProductId());
        deleteProductById(updatedProduct.getProductId());

        assertEquals(updatedProduct, getProduct);
    }

    @Test
    void testDeleteProductById() {

        Products product = new Products();
        product.setProductId(100L);
        product.setProductName("Test Product");
        product.setProductPrice(100.10);
        product.setAmount(100);

        addProducts(product);
        deleteProductById(product.getProductId());

        Products deletedProduct = getProductById(product.getProductId());
        assertNull(deletedProduct);
    }
}