package com.ramcharans.chipotleboot.model;

import lombok.Data;

import java.util.List;

@Data
public class Order {
    private Long id;
    private String customerName;
    private List<Ingredient> ingredients;

    private Double total;
}
