package com.driver;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {
    //      orderId, dpartnerId
    HashMap<Integer, Integer > hm1 = new HashMap<>();
    //      orderId, dpartnerId
    HashMap<Integer,Order> hm2 = new HashMap<>();
    //      dpartnerId, Oject4details ofdelivery
    HashMap<Integer, DeliveryPartner> hm3 = new HashMap<>();
    //      dpartnerId, List of Order
    HashMap<Integer, List<String>> hm4 = new HashMap<>();




}
