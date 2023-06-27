package jdbc.entity;

import java.sql.Date;
import java.util.Objects;

public class Orders {
    private Long orderId;
    private Long userId;
    private String listProducts;
    private Date orderDate;
    private Double orderPrice;

    public Orders() {

    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getListProducts() {
        return listProducts;
    }

    public void setListProducts(String listProducts) {
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
