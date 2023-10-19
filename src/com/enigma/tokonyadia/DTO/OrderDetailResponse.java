package com.enigma.tokonyadia.DTO;

public class OrderDetailResponse {

    private String storeName;
    private String productName;
    private Long productPrice;
    private Integer qty;
    private Long subtotal;

    public OrderDetailResponse(String storeName, String productName, Long productPrice, Integer qty, Long subtotal) {
        this.storeName = storeName;
        this.productName = productName;
        this.productPrice = productPrice;
        this.qty = qty;
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "OrderDetailResponse{" +
                "productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", qty=" + qty +
                ", subtotal=" + subtotal +
                '}';
    }
}
