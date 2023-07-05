package hibernate.dao;

import hibernate.entity.Product;

import java.util.List;

public interface ProductDAO {
    void addProducts(Product product);

    Product getProductById(Long productId);

    List<Product> getAllProducts();

    void updateProductById(Product product);

    void deleteProductById(Product product);
}
