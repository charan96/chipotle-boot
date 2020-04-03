package com.ramcharans.chipotleboot.dao;

import com.ramcharans.chipotleboot.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDAO {
    public void saveOrdertoDB(Order order) {
        // saving order to DB
        System.out.println("Saving Order to DB: " + order.toString());
    }
}
