package jdbc.entity;

public class ShoppingCard {
    private Long cardId;
    private String listProducts;

    public ShoppingCard(){

    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getListProducts() {
        return listProducts;
    }

    public void setListProducts(String listProducts) {
        this.listProducts = listProducts;
    }

    @Override
    public String toString() {
        return "ShoppingCard{" +
                "cardId=" + cardId +
                ", listProducts='" + listProducts + '\'' +
                '}';
    }
}
