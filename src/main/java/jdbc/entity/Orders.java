package jdbc.entity;

import java.sql.Date;

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
