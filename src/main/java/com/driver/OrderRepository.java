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


    public void addOrder(Order order){                               		 // 1.
        ordersDb.put(order.getId(),order);
    }

    public void addPartner(String partnerId){                       		// 2.
        DeliveryPartner dp = new DeliveryPartner(partnerId);
        deliveryPartnersDb.put(partnerId, dp);  // store partnerId followed by its relevant data i.e new partner
        // OR,
        //    deliveryPartnersDb.put(partnerId,new DeliveryPartner(partnerId));
    }

    public void addOrderPartnerPair(String orderId, String partnerId){  		// 3.
        // check order exist or not -order is there in godown and does  partner exists also
        // check order is already  assigned or not
        if(ordersDb.containsKey(orderId) && deliveryPartnersDb.containsKey(partnerId)){
            orderPartnerDb.put(orderId,partnerId);

            List<String> currentOrderList = partnerOrderListDb.getOrDefault(partnerId, new ArrayList<>());
            currentOrderList.add(orderId);
            partnerOrderListDb.put(partnerId, currentOrderList);
            //// OR
//            List<String> currentOrderList = new ArrayList<>();
//            if(partnerOrderListDb.containsKey(partnerId)){
//                currentOrderList = partnerOrderListDb.get(partnerId);
//            }
//            currentOrderList.add(orderId);
//            partnerOrderListDb.put(partnerId,currentOrderList);

            // increase the no of orders of partner
            DeliveryPartner deliveryPartner = deliveryPartnersDb.get(partnerId);
            //  deliveryPartner.setNumberOfOrders(deliveryPartner.getNumberOfOrders()+1);
            ////   OR
            deliveryPartner.setNumberOfOrders(currentOrderList.size());
        }
    }

    public Order getOrderById(String orderId){                      		//  4.
        return ordersDb.get(orderId);
    }

    public DeliveryPartner getPartnerById(String partnerId){        		//  5.
        return deliveryPartnersDb.get(partnerId);
    }

    public int getOrderCountByPartnerId(String partnerId){          		//  6. returning the no.Of Orders
        return partnerOrderListDb.get(partnerId).size();            	// partnerid  ka list size
    }

    public List<String> getOrdersByPartnerId(String partnerId){    		 //  7. RETURNing list of orderId
        return partnerOrderListDb.get(partnerId);                   	//  OR

//        List<String> orders = partnerOrderListDb.getOrDefault(partnerId,new ArrayList<>());
//        return orders;
    }

    public List<String> getAllOrders(){                         		//  8.
        List<String> allOrders = new ArrayList<>();
        //Iterator
        for(String order: ordersDb.keySet()){
            allOrders.add(order);
        }
        return allOrders;
    }

    public int getCountOfUnassignedOrders(){                        		//  9.
        return ordersDb.size() - orderPartnerDb.size(); //total orderListSize - assigned orderSize
    }

    public int getOrdersLeftAfterGivenTimeByPartnerId(int time, String partnerId){  //  10.
//       number of orders that are not delivered by deliverPartner in time
//       1st - get the list of all the order that partner have.
//       2nd - Iterate over list and get the deliverytime
//       3rd - compare the delivery time with the given time - to check the delivery completed within the time or not
        int count = 0;
        List<String> orders = partnerOrderListDb.get(partnerId);

        for(String orderId: orders){
            // Order Class has deliveryTime for the OrderID - so get OrderId & it has its DeliveryTime
            int deliveredTime = ordersDb.get(orderId).getDeliveryTime();
            if(deliveredTime>time)          // greater than given "time" will be the late delivery of Order
                count++;
        }
        return count;
    }

    public int getLastDeliveryTimeByPartnerId(String parterId){		//  11.
//        return is int type bcoz the db has time in int. adn service will convert time from int to string
//       1st- will get the time of delivery of orders by the PartnerId from partmerOrdersListdb.
//       2nd- max time value for delivery will be the last deliveryTime by Deliverypartner for any partnerId
        int maxTime = 0;
        List<String> orders = partnerOrderListDb.get(parterId); // get all the orders for a given partnerId.
        for(String orderId: orders){                            // iterate over the orders to get its deliveryTime of Order
            int deliveredTime = ordersDb.get(orderId).getDeliveryTime();
            maxTime = Math.max(maxTime,deliveredTime);          // everytime math function will check for the MaxTime value
        }

        return maxTime;
    }

    public void deletePartnerById(String partnerId){			//  12.
        //Delete the partnerId - so remove partnerId from deliveryParrtnerDB
        deliveryPartnersDb.remove(partnerId);
        // And push all his assigned orders to unassigned orders - for this 1st need to list all orders of partnerId
        List<String> listOfOrders = partnerOrderListDb.get(partnerId);
        partnerOrderListDb.remove(partnerId);          // delete partnerID from PartnerOrderListdb

        for(String order: listOfOrders){            //  then delete the partnerId from the orderPartnerDb
            orderPartnerDb.remove(order);
        }
    }

    public void deleteOrderById(String orderId){			//  13.
        ordersDb.remove(orderId);                       // remove order from ORDERDB
        String partnerId = orderPartnerDb.get(orderId); // remove from orderPartnerDB by searching by OrderID
        orderPartnerDb.remove(orderId);

        partnerOrderListDb.get(partnerId).remove(orderId);  // will remove the orderID from the partnerId who has been assigned with that orderId
        deliveryPartnersDb.get(partnerId).setNumberOfOrders(partnerOrderListDb.get(partnerId).size()); // decrease the count of orderId assigned to the PartnerID
    }
}
