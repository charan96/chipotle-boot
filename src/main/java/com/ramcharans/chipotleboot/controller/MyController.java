package com.ramcharans.chipotleboot.controller;

import com.ramcharans.chipotleboot.model.Ingredient;
import com.ramcharans.chipotleboot.model.Order;
import com.ramcharans.chipotleboot.service.IngredientsService;
import com.ramcharans.chipotleboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/* NOTE 1: Controllers are expected to be dumb; all they care about are the end-user; should
   NOTE 1 contd.: delegate all the heavy lifting to the service objects; should perform only
   NOTE 1 contd.: input validation and converting business-specific exceptions to exception messages
   NOTE 1 contd.: understandable and helpful to the user.

   NOTE 2: Spring Beans should not contain instance variables since they are singleton and are shared across
   NOTE 2 contd.: threads. If they do, instance variables act as a single common resource for multiple threads
   NOTE 2 contd.: leading to unexpected behavior.

   NOTE 3: The order object created in line "modelMap.addAttribute("order", new Order())" is merely
   NOTE 3 contd.: to help Spring understand what fields need to be populated. The order object
   NOTE 3 contd.: in line "public String submitOrder(@ModelAttribute Order order, ModelMap modelMap)"
   NOTE 3 contd.: is a new Order object that was automatically populated by Spring from the fields
   NOTE 3 contd.: defined in the home.html form. If you look at the debug messages, it can be seen that
   NOTE 3 contd.: the Order object is created twice: once in the getHome() method and once in submitOrder() method.
   NOTE 3 contd.: The two Order objects are two separate objects. @ModelAttribute tells Spring to automatically
   NOTE 3 contd.: populate the Order object with the fields from the HTML form.

   NOTE 4: Spring Applications can be separated into 3 layers: Data Access, Service and Presentation. Data Access
   NOTE 4 contd.: can only be utilized by the Service classes. The Service objects are used by the Presentation
   NOTE 4 contd.: classes. Presentation classes should not call the Data Access objects directly; it should be left to the
   NOTE 4 contd.: service objects to deal with them. Controllers fall into the Presentation Layer. The only abstraction
   NOTE 4 contd.: that can be shared across all layers should be the Domain Objects/Models. Classes from the same layer
   NOTE 4 contd.: can use each otehr.
 */

@Controller
public class MyController {
    @Autowired
    IngredientsService ingredientsService;

    @Autowired
    OrderService orderService;

    @GetMapping("/")
    public String getHome(ModelMap modelMap) {
        List<Ingredient> availableIngredients = ingredientsService.getAvailableIngredients();

        modelMap.addAttribute("available_ingredients", availableIngredients);
        modelMap.addAttribute("order", new Order());

        return "home";
    }

    @PostMapping("/order")
    public String submitOrder(@ModelAttribute Order order, ModelMap modelMap) {
        order = orderService.setOrderIdAndTotal(order);
        orderService.saveOrder(order);

        modelMap.addAttribute("filled_order", order);

        return "order_submit";
    }
}
