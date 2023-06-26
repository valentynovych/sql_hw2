package jdbc.entity;

public class ShoppingCard {
    private Long cardId;
    private Long product_id;

    public ShoppingCard() {

    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "ShoppingCard{" +
                "cardId=" + cardId +
                ", product_id=" + product_id +
                '}';
    }
}
