package com.enigma.tokonyadia.DTO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OrderResponse {

    private Integer orderId;
    private Date transDate;
    private String customerName;
    private List<OrderDetailResponse> orderDetails = new ArrayList<>();

    public OrderResponse(Integer orderId, Date transDate, String customerName) {
        this.orderId = orderId;
        this.transDate = transDate;
        this.customerName = customerName;
    }

    public void setOrderDetails(OrderDetailResponse orderDetails) {
        this.orderDetails.add(orderDetails);
    }

    @Override
    public String toString() {
        return "OrderResponse{" +
                "orderId=" + orderId +
                ", transDate=" + transDate +
                ", customerName='" + customerName + '\'' +
                ", orderDetails=" + orderDetails +
                '}';
    }
}
