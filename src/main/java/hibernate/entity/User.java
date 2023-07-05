package hibernate.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "orders_count")
    private Integer ordersCount;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "details_id")
    private UserDetails detailsId;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "shopping_cart",
            joinColumns = { @JoinColumn(name = "card_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") })
    private List<Product> products = new ArrayList<>();
    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    public User() {

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

    public UserDetails getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(UserDetails detailsId) {
        this.detailsId = detailsId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId.equals(user.userId)
                && firstName.equals(user.firstName)
                && ordersCount.equals(user.ordersCount)
                && detailsId.equals(user.detailsId)
                && products.equals(user.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, ordersCount, detailsId, products);
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", detailsId=" + detailsId +
                ", cardId=" + products +
                '}';
    }
}
