package mvc.java.controller;

import mvc.java.model.User;
import mvc.java.service.UserNotFoundException;
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
public class UsersController {
    @Autowired
    private UserService service;

    @GetMapping("/users")
    public String showUserList(Model model){
        List<User> Users= service.listAll();
        model.addAttribute("users", Users);
        return "users/index";
    }

    @GetMapping("/users/create")
    public String showNewForm(Model model){
        model.addAttribute("user", new User());
        return "users/form";
    }

    @PostMapping("/users/save")
    public String saveUser(User User, RedirectAttributes ra){
        service.save(User);
        ra.addFlashAttribute("message", "Thêm thành công");
        return "redirect:/users";
    }

    @GetMapping("/users/detail/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes ra){
        try {
            User User = service.get(id);
            model.addAttribute("user", user);
            return "users/form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", "Thêm thành công");
            return "redirect:/users";
        }
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, RedirectAttributes ra){
        try{
            service.delete(id);
        }catch (UserNotFoundException e){
            ra.addFlashAttribute("message", "Xóa thành công");
        }
        return "redirect:/users";
    }
}
