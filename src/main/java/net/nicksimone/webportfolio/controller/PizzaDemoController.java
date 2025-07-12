package net.nicksimone.webportfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pizza")
public class PizzaDemoController {

    @GetMapping("")
    public String showPizzaHome(Model model){
        model.addAttribute("pageTitle", "Pizza Home");
        return "pizza/pizza-home";
    }

   @GetMapping("/web-tour")
    public String viewWebTour(Model model){
        model.addAttribute("pageTitle", "Web Tour");
        return "pizza/web-tour";
    }

    @GetMapping("/order")
    public String viewOrderForm(Model model){
        model.addAttribute("pageTitle", "Pizza Order");
        return "pizza/order-pizza";
    }

    @GetMapping("/view-orders")
    public String viewPlacedOrders(Model model){
        model.addAttribute("pageTitle", "View Orders");
        return "pizza/view-orders";
    }

    @GetMapping("/employee-login")
    public String viewEmployeeLogin(Model model){
        model.addAttribute("pageTitle", "Employee Login");
        return "pizza/employee-login";
    }

    @GetMapping("/employee-homepage")
    public String viewEmployeeHomepage(Model model) {
        model.addAttribute("pageTitle", "Employee Home");
        return "pizza/employee-homepage";
    }
}
