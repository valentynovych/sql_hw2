package jdbc.dao;

import jdbc.entity.Products;

import java.sql.SQLException;
import java.util.List;

public interface ProductsDAO {

    void addProducts(Products product) throws SQLException;

    Products getProductById(Long productId);

    List<Products> getAllProducts() throws SQLException;

    void updateProductById(Long productId, Products product);

    void deleteProductById(Long productId);
}
