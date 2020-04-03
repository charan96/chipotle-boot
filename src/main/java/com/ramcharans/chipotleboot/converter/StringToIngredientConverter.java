package com.ramcharans.chipotleboot.converter;

import com.ramcharans.chipotleboot.dao.IngredientsDAO;
import com.ramcharans.chipotleboot.model.Ingredient;
import com.ramcharans.chipotleboot.service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/* this class is crucial since home.html only gets the Ingredient IDs from the checkboxes as strings. We need to define
how to convert this string into the object. The overridden convert method below tells Spring how to convert a String to
the Ingredient Object.

In Spring, we'd have to manually register this class as a converter. With Spring Boot? Just add @Component and Spring
 Boot will register it automatically during scanning. It's all Magic!
 */

@Component
public class StringToIngredientConverter implements Converter<String, Ingredient> {
    @Autowired
    IngredientsService ingredientsService;

    @Override
    public Ingredient convert(String s) {
        return ingredientsService.getIngredientById(Long.valueOf(s));
    }
}
