package com.dennis.product_management_system.controller;

import com.dennis.product_management_system.model.Product;
import com.dennis.product_management_system.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class WebController {

    private final ProductService productService;

    public WebController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String getAllProducts(Model model,
                                 @RequestParam(value = "page", defaultValue = "0") int page,
                                 @RequestParam(value = "size", defaultValue = "5") int size,
                                 @RequestParam(value = "sortField", defaultValue = "name") String sortField,
                                 @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir) {
        Page<Product> productPage = productService.getProductsPaginated(page, size, sortField, sortDir);
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("totalItems", productPage.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        return "index";
    }

    @GetMapping("/products/new")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }

    @PostMapping("/products")
    public String addProduct(@ModelAttribute Product product) {
        productService.addProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/products/{id}")
    public String viewProduct(@PathVariable long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "view-product";
    }

    @GetMapping("/products/edit/{id}")
    public String showEditProductForm(@PathVariable long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "edit-product";
    }

    @PostMapping("/products/edit/{id}")
    public String updateProduct(@PathVariable long id, @ModelAttribute Product product) {
        productService.updateProduct(id, product);
        return "redirect:/products";
    }

    @PostMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @GetMapping("/products/search")
    public String searchProducts(@RequestParam(name = "q", required = false, defaultValue = "") String searchTerm, Model model) {
        if (searchTerm.isEmpty()) {
            // Handle case where searchTerm is empty or not provided
            model.addAttribute("products", productService.getAllProducts()); // or any other appropriate handling
        } else {
            List<Product> foundProducts = productService.searchProducts(searchTerm);
            model.addAttribute("products", foundProducts);
        }
        return "search_result";
    }

}
