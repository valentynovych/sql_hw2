package jdbc.dao;

public interface ShoppingCardDAO {
    void addProductToCard(String productName);

    void deleteProductByName(String productName);

    String getAllProducts();

    void clearShoppingCardById(Long cardId);
}
