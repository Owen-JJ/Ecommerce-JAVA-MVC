package mvc.java.controller;

import mvc.java.model.Product;
import mvc.java.service.ProductNotFoundException;
import mvc.java.service.ProductService;
import mvc.java.service.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
//@RequestMapping("/products")
public class ProductsController {
   @Autowired private ProductService service;

   @GetMapping("/products")
   public String showProductList(Model model){
       List<Product> products= service.listAll();
       model.addAttribute("products", products);
       return "products/index";
   }

   @GetMapping("/products/create")
    public String showNewForm(Model model){
       model.addAttribute("product", new Product());
       return "products/form";
   }

   @PostMapping("/products/save")
    public String saveProduct(Product product, RedirectAttributes ra){
       service.save(product);
       ra.addFlashAttribute("message", "Thêm thành công");
       return "redirect:/products";
   }

   @GetMapping("/products/detail/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes ra){
       try {
           Product product = service.get(id);
           model.addAttribute("product", product);
           return "products/form";
       } catch (ProductNotFoundException e) {
           ra.addFlashAttribute("message", "Thêm thành công");
           return "redirect:/products";
       }
   }

   @GetMapping("/products/delete/{id}")
   public String deleteProduct(@PathVariable("id") Long id, RedirectAttributes ra){
       try{
           service.delete(id);
       }catch (ProductNotFoundException e){
           ra.addFlashAttribute("message", "Xóa thành công");
       }
       return "redirect:/products";
   }
}
