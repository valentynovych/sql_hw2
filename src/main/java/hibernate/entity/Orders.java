package hibernate.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Orders implements Serializable {
    @Id
    @Column(name = "order_id")
    private Long orderId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users userId;

    @OneToMany
    @JoinColumn(name = "product_id")
    @Column(name = "list_products")
    private List<Products> listProducts;
    @Column(name = "order_date")
    private Date orderDate;
    @Column(name = "order_price")
    private Double orderPrice;

    public Orders() {

    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public List<Products> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<Products> listProducts) {
        this.listProducts = listProducts;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
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
        Orders orders = (Orders) o;
        return Objects.equals(orderId, orders.orderId)
                && Objects.equals(userId, orders.userId)
                && Objects.equals(listProducts, orders.listProducts)
                && Objects.equals(orderDate, orders.orderDate)
                && Objects.equals(orderPrice, orders.orderPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, userId, listProducts, orderDate, orderPrice);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", listProducts='" + listProducts + '\'' +
                ", orderDate=" + orderDate +
                ", orderPrice=" + orderPrice +
                '}';
    }
}
