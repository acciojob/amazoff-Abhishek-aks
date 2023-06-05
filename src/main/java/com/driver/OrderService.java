package com.driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public void addOrder(Order order){         orderRepository.addOrder(order);
    }
    public void addPartner(String partnerId){        orderRepository.addPartner(partnerId);
    }
    public void addOrderPartnerPair(String orderId, String partnerId){         orderRepository.addOrderPartnerPair(orderId,partnerId);
    }
    public Order getOrderById(String orderId){         return orderRepository.getOrderById(orderId);
    }
    public DeliveryPartner getPartnerById(String partnerId){         return orderRepository.getPartnerById(partnerId);
    }
    public int getOrderCountByPartnerId(String partnerId){         return orderRepository.getOrderCountByPartnerId(partnerId);
    }
    public List<String> getOrdersByPartnerId(String partnerId){         return orderRepository.getOrdersByPartnerId(partnerId);
    }
    public List<String> getAllOrders(){         return orderRepository.getAllOrders();
    }
    public int getCountOfUnassignedOrders(){         return orderRepository.getCountOfUnassignedOrders();
    }

    public int getOrdersLeftAfterGivenTimeByPartnerId(String deliveryTime, String partnerId){
// should convert time here  string to int
        String time[] = deliveryTime.split(":");
        int newTime = Integer.parseInt(time[0])*60 + Integer.parseInt(time[1]);

        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(newTime, partnerId);
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId){
// return int to string which is done in repo only
        int time = orderRepository.getLastDeliveryTimeByPartnerId(partnerId);
        String HH = String.valueOf(time/60);
        String MM = String.valueOf(time%60);

        if(HH.length()<2)
            HH = '0' + HH;
        if(MM.length()<2)
            MM = '0' + MM;

        return HH+':'+MM;
    }

    public void deletePartnerById(String partnerId){
        orderRepository.deletePartnerById(partnerId);
    }

    public void deleteOrderById(String orderId){
        orderRepository.deleteOrderById(orderId);
    }
}
