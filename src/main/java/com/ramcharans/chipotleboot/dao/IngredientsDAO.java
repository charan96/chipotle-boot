package com.ramcharans.chipotleboot.dao;

import com.ramcharans.chipotleboot.model.Ingredient;
import com.ramcharans.chipotleboot.model.Ingredient.Type;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class IngredientsDAO {

    public List<Ingredient> loadIngredientsTableFromDB() {
        System.out.println("loading ingredients from DB");

        List<Ingredient> ingredients = new ArrayList<>();

        ingredients.add(new Ingredient(100L, "White Rice", Type.RICE, 0.0));
        ingredients.add(new Ingredient(101L, "Brown Rice", Type.RICE, 0.0));
        ingredients.add(new Ingredient(102L, "Black Beans", Type.BEANS, 0.0));
        ingredients.add(new Ingredient(103L, "Pinto Beans", Type.BEANS, 0.0));
        ingredients.add(new Ingredient(104L, "Chicken", Type.MEAT, 6.80));
        ingredients.add(new Ingredient(105L, "Steak", Type.MEAT, 7.80));
        ingredients.add(new Ingredient(106L, "Carnitas", Type.MEAT, 7.20));
        ingredients.add(new Ingredient(107L, "Cheese", Type.ADDON, 0.50));
        ingredients.add(new Ingredient(108L, "Corn", Type.ADDON, 0.0));
        ingredients.add(new Ingredient(109L, "Sour Cream", Type.ADDON, 0.0));
        ingredients.add(new Ingredient(110L, "Guacamole", Type.ADDON, 1.0));
        ingredients.add(new Ingredient(111L, "Salsa", Type.ADDON, 0.0));

        return ingredients;
    }
}