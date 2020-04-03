package com.ramcharans.chipotleboot.service;

import com.ramcharans.chipotleboot.dao.IngredientsDAO;
import com.ramcharans.chipotleboot.dao.OrderDAO;
import com.ramcharans.chipotleboot.model.Ingredient;
import com.ramcharans.chipotleboot.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    IngredientsService ingredientsService;

    @Autowired
    OrderDAO orderDAO;

    private Long createNewOrderId() {
        return Math.abs(new Random().nextLong());
    }

    public Order setOrderIdAndTotal(Order order) {
        order.setId(createNewOrderId());
        order.setTotal(calculateOrderTotal(order));
        return order;
    }

    public Double calculateOrderTotal(Order order) {
        // business logic of calculating order total goes here

        Double minMeatPrice = Collections.min(ingredientsService.getAvailableIngredients()
                .stream()
                .filter(ingredient -> ingredient.getType().equals(Ingredient.Type.MEAT))
                .map(Ingredient::getPrice)
                .collect(Collectors.toList()));

        Double maxOrderMeatPrice = order.getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getType().equals(Ingredient.Type.MEAT))
                .max(Comparator.comparing(Ingredient::getPrice))
                .map(Ingredient::getPrice)
                .orElse(minMeatPrice);

        Double sumOrderAddOnPrice = order.getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getType().equals(Ingredient.Type.ADDON))
                .map(Ingredient::getPrice)
                .reduce(0.0, Double::sum);

        return maxOrderMeatPrice + sumOrderAddOnPrice;
    }

    public void saveOrder(Order order) {
        orderDAO.saveOrdertoDB(order);
    }
}
