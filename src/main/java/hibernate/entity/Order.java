package hibernate.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> listProducts;
    @Column(name = "order_date")
    private LocalDateTime orderDate;
    @Column(name = "order_price")
    private Double orderPrice;

    public Order() {

    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public List<Product> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<Product> listProducts) {
        this.listProducts = listProducts;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId)
                && Objects.equals(userId, order.userId)
                && Objects.equals(listProducts, order.listProducts)
                && Objects.equals(orderDate, order.orderDate)
                && Objects.equals(orderPrice, order.orderPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, userId, listProducts, orderDate, orderPrice);
    }

    @Override
    public String toString() {
        return " Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", listProducts=" + listProducts +
                ", orderDate=" + orderDate +
                ", orderPrice=" + orderPrice +
                '}';
    }
}
