package com.driver;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {
    //      orderId, dpartnerId
    HashMap<String, String > idb = new HashMap<>();
    //      orderId, Object4 OrderDetails
    HashMap<String,Order> odb = new HashMap<>();
    //      dpartnerId, Oject4 Deliverydetails
    HashMap<String, DeliveryPartner> pdb = new HashMap<>();
    //      dpartnerId, List of Order
    HashMap<String, List<String>> dpolist = new HashMap<>();

    // in db add me return nhi hoga
    public void addOrder(Order order){
        odb.put(order.getId(),order);
    }
    public void addPartner(String partnerId) {
    }

    public void addOrderPartnerPair(String orderId, String partnerId) {
    }

    public Order getOrderByID(String orderId) {
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
