package com.AIPS.Electricals.controllers;

import com.AIPS.Electricals.models.CartItem;
import com.AIPS.Electricals.models.Order;
import com.AIPS.Electricals.models.Product;
import com.AIPS.Electricals.repositories.CartItemRepository;
import com.AIPS.Electricals.repositories.OrderRepository;
import com.AIPS.Electricals.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    // Home page
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // Products page - load from DB

    @GetMapping("/products")
    public String showProductsPage(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "products";
    }


    // Static pages
    @GetMapping("/service")
    public String services() {
        return "services";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/enquiry")
    public String enquiry() {
        return "enquiry";
    }

    @GetMapping("/order")
    public String order() {
        return "order";
    }

    @GetMapping("/admin/login")
    public String adminLogin() {
        return "admin-login";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin-dashboard";
    }

    // View cart
    @GetMapping("/cart")
    public String viewCart(Model model) {
        List<CartItem> cartItems = cartItemRepository.findAll();
        double total = cartItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", total);
        return "cart";
    }

    // Add to cart
    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam String name,
                            @RequestParam String type,
                            @RequestParam double price,
                            @RequestParam(defaultValue = "1") int quantity,
                            @RequestParam(required = false) Long referenceId) {
        CartItem item = new CartItem(name, type, price, quantity, referenceId);
        cartItemRepository.save(item);
        return "redirect:/cart";
    }

    // Remove item from cart
    @GetMapping("/cart/remove/{id}")
    public String removeCartItem(@PathVariable Long id) {
        cartItemRepository.deleteById(id);
        return "redirect:/cart";
    }

    // Checkout form
    @GetMapping("/checkout")
    public String checkoutForm(Model model) {
        double total = cartItemRepository.findAll().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        model.addAttribute("total", total);
        return "checkout";
    }

    // Handle order submission
    @PostMapping("/checkout")
    public String checkout(@RequestParam String customerName,
                           @RequestParam String address,
                           @RequestParam String phone,
                           @RequestParam String email,
                           RedirectAttributes redirectAttributes) {

        List<CartItem> items = cartItemRepository.findAll();
        double total = items.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        Order order = new Order(customerName, address, email, totalPrice);
        order.setItems(items); // Make sure Order.java has a List<CartItem> items field
        orderRepository.save(order);

        cartItemRepository.deleteAll(); // Clear cart after placing order

        redirectAttributes.addFlashAttribute("success", "Your order has been placed successfully!");
        return "redirect:/thank-you";
    }

    // Thank you page
    @GetMapping("/thank-you")
    public String thankYouPage() {
        return "thank-you";
    }
}
	