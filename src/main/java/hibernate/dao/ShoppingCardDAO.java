package hibernate.dao;


import hibernate.entity.Product;
import hibernate.entity.ShoppingCard;

import java.util.List;

public interface ShoppingCardDAO {
    void addProductToCard(ShoppingCard shoppingCard);

    void deleteProductById(Long cardId, Long productId);

    List<Product> getAllProducts(Long cardId);

    void clearShoppingCardById(Long cardId);
}
