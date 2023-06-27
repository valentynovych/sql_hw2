package jdbc.entity;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCard that = (ShoppingCard) o;
        return Objects.equals(cardId, that.cardId) && Objects.equals(product_id, that.product_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, product_id);
    }

    @Override
    public String toString() {
        return "ShoppingCard{" +
                "cardId=" + cardId +
                ", product_id=" + product_id +
                '}';
    }
}
