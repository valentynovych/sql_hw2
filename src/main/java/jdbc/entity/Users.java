package jdbc.entity;

import java.util.Objects;

public class Users {
    private Long userId;
    private String firstName;
    private Integer ordersCount;
    private Long detailsId;
    private Long cardId;

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

    public Long getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(Long detailsId) {
        this.detailsId = detailsId;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
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
