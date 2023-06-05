package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

@Repository
public class OrderRepository {
    //      orderId, Object4 OrderDetails
    HashMap<String,Order> ordersDb = new HashMap<>();
    //      dpartnerId, Oject4 Deliverydetails
    HashMap<String, DeliveryPartner> deliveryPartnersDb = new HashMap<>();

    //      orderId,dpartnerId
    HashMap<String, String> orderPartnerDb = new HashMap<>();
    //      dpartnerId, List of Order
    HashMap<String, List<String>> partnerOrderListDb = new HashMap<>();


    // in db add me return nhi hoga
    public void addOrder(Order order){
        ordersDb.put(order.getId(),order); // store orderId followed by its relevant data
    }
    public void addPartner(String partnerId) {
        DeliveryPartner dp = new DeliveryPartner();
        deliveryPartnersDb.put(partnerId,dp);  // store partnerId followed by its relevant data
    }

    public void addOrderPartnerPair(String orderId, String partnerId){
        if(ordersDb.containsKey(orderId) && deliveryPartnersDb.containsKey(partnerId)){
            orderPartnerDb.put(orderId,partnerId);

            List<String> currentOrders = new ArrayList<>();

            if(partnerOrderListDb.containsKey(partnerId)){
                currentOrders = partnerOrderListDb.get(partnerId);
            }

            currentOrders.add(orderId);
            partnerOrderListDb.put(partnerId,currentOrders);

            // increase the no of orders of partner
            DeliveryPartner deliveryPartner = deliveryPartnersDb.get(partnerId);
            deliveryPartner.setNumberOfOrders(currentOrders.size());
        }
    }


    public Order getOrderByID(String orderId) {
        return ordersDb.get(orderId);
    }

    public DeliveryPartner getPartnerById(String partnerId){
        return deliveryPartnersDb.get(partnerId);
    }

    public int getOrderCountByPartnerId(String partnerId){
        return partnerOrderListDb.get(partnerId).size();    //returning no.of orders
    }

    public List<String> getOrdersByPartnerId(String partnerId){
        return partnerOrderListDb.get(partnerId);      // returning list of order
    }

    public List<String> getAllOrders(){
        List<String> orders = new ArrayList<>();
        for(String order: ordersDb.keySet()){
            orders.add(order);
        }
        return orders;
    }

    public int getCountOfUnassignedOrders(){
        return ordersDb.size() - orderPartnerDb.size();
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(int deliveryTime, String partnerId){

        Integer ordercount =0;
        List<String> l = partnerOrderListDb.get(partnerId);    // get the orderlist partnerId have

        for (String s : l) {        // iterate over the list
            Order order = ordersDb.get(s);
            if (order.getDeliveryTime() > deliveryTime) {
                ordercount++;
            }
        }
        return ordercount;
    }

    public int getLastDeliveryTimeByPartnerId(String partnerId){
        int maxTime = 0;
        List<String> orders = partnerOrderListDb.get(partnerId); // get all the orders for a given partnerId.
        for(String orderId: orders){                            // iterate over the orders to get its deliveryTime of Order
            int deliveredTime = ordersDb.get(orderId).getDeliveryTime();
            maxTime = Math.max(maxTime,deliveredTime);          // everytime math function will check for the MaxTime value
        }

        return maxTime;


    }

    public void deletePartnerById(String partnerId){
        deliveryPartnersDb.remove(partnerId);
        List<String> listOfOrders = partnerOrderListDb.get(partnerId);
        partnerOrderListDb.remove(partnerId);

        for(String order: listOfOrders){
            orderPartnerDb.remove(order);
        }
    }

    public void deleteOrderById(String orderId){
        ordersDb.remove(orderId);
        String partnerId = orderPartnerDb.get(orderId);
        orderPartnerDb.remove(orderId);

        partnerOrderListDb.get(partnerId).remove(orderId);
        deliveryPartnersDb.get(partnerId).setNumberOfOrders(partnerOrderListDb.get(partnerId).size());
    }

}
