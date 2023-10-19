package com.enigma.tokonyadia.DTO;

public class OrderDetailRequest {

    private Integer productId;
    private Integer quantity;
    private Long price;

    public OrderDetailRequest(Integer productId, Integer quantity, Long price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderDetailRequest(){}

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
