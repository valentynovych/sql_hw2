package jdbc.dao;

import jdbc.entity.ShoppingCard;

import java.util.List;

public interface ShoppingCardDAO {
    void addProductToCard(ShoppingCard shoppingCard);


    void deleteProductById(Long cardId, Long productId);

    List<Long> getAllProducts(Long cardId);

    void clearShoppingCardById(Long cardId);
}
