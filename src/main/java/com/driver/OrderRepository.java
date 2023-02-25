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
}
