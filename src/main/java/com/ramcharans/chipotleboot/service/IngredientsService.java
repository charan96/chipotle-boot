package com.ramcharans.chipotleboot.service;

import com.ramcharans.chipotleboot.dao.IngredientsDAO;
import com.ramcharans.chipotleboot.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientsService {
    @Autowired
    IngredientsDAO ingredientsDAO;

    public List<Ingredient> getAvailableIngredients() {
        return ingredientsDAO.loadIngredientsTableFromDB();
    }

    public Ingredient getIngredientById(Long id) {
        List<Ingredient> ingredients = getAvailableIngredients();

        Ingredient ing = null;

        for (Ingredient ingredient : ingredients) {
            if (ingredient.getId().equals(id))
                ing = ingredient;
        }

        return ing;
    }
}
