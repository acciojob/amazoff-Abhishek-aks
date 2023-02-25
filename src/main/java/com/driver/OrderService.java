package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository Orepo;


    public void addOrder(Order order) {
    }

    public void addPartner(String partnerId) {
    }

    public void addOrderPartnerPair(String orderId, String partnerId) {
    }


    public Order getOrderById(String orderId) {
    }

    public DeliveryPartner getPartnerById(String partnerId) {
    }

    public Integer getOrderCountByPartnerId(String partnerId) {
    }

    public List<String> getOrdersByPartnerId(String partnerId) {
    }

    public List<String> getAllOrders(List<String> orders) {
    }

    public Integer getCountOfUnassignedOrders() {
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId) {
    }

    public void deletePartnerId(String partnerId) {
    }

    public void deleteOrderById(String orderId) {
    }
}
