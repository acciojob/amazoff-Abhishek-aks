package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {
    //      orderId,dpartnerId
    HashMap<String, String> idb = new HashMap<>();
    //      orderId, Object4 OrderDetails
    HashMap<String,Order> orderdb = new HashMap<>();
    //      dpartnerId, Oject4 Deliverydetails
    HashMap<String, DeliveryPartner> partnerdb = new HashMap<>();
    //      dpartnerId, List of Order
    HashMap<String, List<String>> dpolist = new HashMap<>();

    // in db add me return nhi hoga
    public void addOrder(Order order){
        orderdb.put(order.getId(),order); // store orderId followed by its relevant data
    }
    public void addPartner(String partnerId) {
        DeliveryPartner dp = new DeliveryPartner();
        partnerdb.put(partnerId,dp);  // store partnerId followed by its relevant data
    }

    public void addOrderPartnerPair(String orderId, String partnerId) {
        List<String> list = dpolist.getOrDefault(partnerId, new ArrayList<>());
        list.add(orderId);
        dpolist.put(partnerId, list);
        idb.put(orderId, partnerId);

    }

    public Order getOrderByID(String orderId) {
        for(String s : orderdb.keySet()){
            if(s.equals(orderId)){
                return orderdb.get(s);
            }
        }
        return null;
    }

    public DeliveryPartner getPartnerById(String partnerId) {
        if(partnerdb.containsKey(partnerId)){
            return partnerdb.get(partnerId);
        }
        return null;
    }

    public Integer getOrderCountByPartnerId(String partnerId) {
        int orders = dpolist.getOrDefault(partnerId, new ArrayList<>()).size());
        return orders;
    }

    public List<String> getOrdersByPartnerId(String partnerId) {
        List<String> orders = dpolist.getOrDefault(partnerId,new Arraylist<>().size());
        return orders;
    }

    public List<String> getAllOrders(List<String> orders) {
        List<String> allorder = new ArrayList<>();
        for(String sb : orderdb.keySet()){
            allorder.add(sb);
        }
        return allorder;
    }

    public Integer getCountOfUnassignedOrders() {
        int countOrders;
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
