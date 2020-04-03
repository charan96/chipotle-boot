package com.ramcharans.chipotleboot.model;

import lombok.*;

@Data
@AllArgsConstructor
public class Ingredient {
    public static enum Type {RICE, MEAT, BEANS, ADDON}

    private Long id;
    private String name;
    private Type type;
    private Double price;

}
