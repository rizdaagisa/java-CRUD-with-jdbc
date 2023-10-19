package com.enigma.tokonyadia.DTO;

import java.util.List;

public class OrderRequest {

    private Integer customerId;
    private List<OrderDetailRequest> orderDetailRequests;

    public OrderRequest(){}

    public OrderRequest(Integer customerId,  List<OrderDetailRequest> orderDetailRequests) {
        this.customerId = customerId;
        this.orderDetailRequests = orderDetailRequests;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public List<OrderDetailRequest> getOrderDetailRequests() {
        return orderDetailRequests;
    }

    public void setOrderDetailRequests(List<OrderDetailRequest> orderDetailRequests) {
        this.orderDetailRequests = orderDetailRequests;
    }
}
