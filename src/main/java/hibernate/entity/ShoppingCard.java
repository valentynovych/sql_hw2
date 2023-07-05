package hibernate.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "card_id")
    private Long cardId;
    @OneToMany
    @JoinColumn(name = "product_id")
    private List<Product> productId = new ArrayList<>();

    public ShoppingCard() {

    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public List<Product> getProductId() {
        return productId;
    }

    public void setProductId(List<Product> product_id) {
        this.productId = product_id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCard that = (ShoppingCard) o;
        return Objects.equals(cardId, that.cardId) && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, productId);
    }

    @Override
    public String toString() {
        return "ShoppingCard{" +
                "cardId=" + cardId +
                ", product_id=" + productId +
                '}';
    }
}
