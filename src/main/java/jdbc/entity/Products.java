package jdbc.entity;

import java.util.Objects;

public class Products {
    private Long productId;
    private String productName;
    private Double productPrice;
    private Integer amount;

    public Products() {

    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Products products = (Products) o;
        return Objects.equals(productId, products.productId)
                && Objects.equals(productName, products.productName)
                && Objects.equals(productPrice, products.productPrice)
                && Objects.equals(amount, products.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, productPrice, amount);
    }

    @Override
    public String toString() {
        return "Products{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", amount=" + amount +
                '}';
    }
}
