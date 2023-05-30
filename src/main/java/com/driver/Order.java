package com.driver;

public class Order {
    private String id;
    private int deliveryTime;
    // Constructor
    public Order() {
    }

    public Order(String id, String deliveryTime) {
        this.id = id;
        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        String [] sep = deliveryTime.split(":");	// Split method return array of strings
        int time = (Integer.parseInt(sep[0])*60)+ (Integer.parseInt(sep[1])); // Integer.parseInt() method converts String to int
        this.deliveryTime = time;
    }
    // Getter & Setter
    public String getId() { return id; }
    public int getDeliveryTime() { return deliveryTime; }

    public void  setId(String id) { this. id = id;}
    public void  setDeliveryTime(int deliverytime) { this. deliveryTime = deliverytime;}
}


