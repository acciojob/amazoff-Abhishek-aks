package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository Orderepo;


    public void addOrder(Order order) {
        Orderepo.addOrder(order);
    }

    public void addPartner(String partnerId) {
        Orderepo.addPartner(partnerId);
    }

    public void addOrderPartnerPair(String orderId, String partnerId) {
        Orderepo.addOrderPartnerPair(orderId,partnerId);
    }

    public Order getOrderById(String orderId) {
        return Orderepo.getOrderByID(orderId);
    }

    public DeliveryPartner getPartnerById(String partnerId) {
        return Orderepo.getPartnerById(partnerId);
    }

    public Integer getOrderCountByPartnerId(String partnerId) {
        return getOrderCountByPartnerId(partnerId);
    }

    public List<String> getOrdersByPartnerId(String partnerId) {
        return getOrdersByPartnerId(partnerId);
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
