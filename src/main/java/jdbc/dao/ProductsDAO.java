package jdbc.dao;

import jdbc.entity.Products;

import java.util.List;

public interface ProductsDAO {
    void addProducts(Products product);
    Products getProductById(Long productId);
    List<Products> getAllProducts();
    void updateProductById(Long productId, Products product);
    void deleteProductById(Long productId);
}
