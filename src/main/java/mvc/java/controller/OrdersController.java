package mvc.java.controller;


import mvc.java.model.Order;
import mvc.java.model.User;
import mvc.java.service.OrderNotFoundException;
import mvc.java.service.OrderService;
import mvc.java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class OrdersController {
    @Autowired
    private OrderService service;
    @Autowired private UserService usr;
    @GetMapping("/orders")
    public String showUserList(Model model){
        List<Order> Orders= service.listAll();
        model.addAttribute("orders", Orders);
        return "orders/index";
    }

    @GetMapping("/orders/create")
    public String showNewForm(Model model){
        model.addAttribute("user", new Order());
        return "orders/form";
    }

    @PostMapping("/orders/save")
    public String saveUser(Order order, RedirectAttributes ra){
        service.save(order);
        ra.addFlashAttribute("message", "Thêm thành công");
        return "redirect:/orders";
    }

    @GetMapping("/orders/detail/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes ra){
        try {
            Order order = service.get(id);
            model.addAttribute("Order", order);
            return "orders/form";
        } catch (OrderNotFoundException e) {
            ra.addFlashAttribute("message", "Thêm thành công");
            return "redirect:/orders";
        }
    }

    @GetMapping("/orders/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, RedirectAttributes ra){
        try{
            service.delete(id);
        }catch (OrderNotFoundException e){
            ra.addFlashAttribute("message", "Xóa thành công");
        }
        return "redirect:/orders";
    }
}
