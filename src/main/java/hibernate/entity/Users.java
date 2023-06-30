package hibernate.entity;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "orders_count")
    private Integer ordersCount;
    @OneToOne
    @JoinColumn(name = "details_id")
    private UsersDetails detailsId;
    @OneToMany
    @JoinColumn(name = "card_id")
    @Column(name = "card_id")
    private List<ShoppingCard> cardId;

    public Users() {

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getOrdersCount() {
        return ordersCount;
    }

    public void setOrdersCount(Integer ordersCount) {
        this.ordersCount = ordersCount;
    }

    public UsersDetails getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(UsersDetails detailsId) {
        this.detailsId = detailsId;
    }

    public List<ShoppingCard> getCardId() {
        return cardId;
    }

    public void setCardId(List<ShoppingCard> cardId) {
        this.cardId = cardId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return userId.equals(users.userId)
                && firstName.equals(users.firstName)
                && ordersCount.equals(users.ordersCount)
                && detailsId.equals(users.detailsId)
                && cardId.equals(users.cardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, ordersCount, detailsId, cardId);
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", detailsId=" + detailsId +
                ", cardId=" + cardId +
                '}';
    }
}
