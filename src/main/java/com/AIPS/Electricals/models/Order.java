package com.AIPS.Electricals.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer_orders") // 'order' is a reserved keyword in SQL
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String email;
    private String address;

    private double totalPrice;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id") // creates a foreign key in CartItem
    private List<CartItem> items;

    public Order() {}

    // Constructor with all fields (already existing)
    public Order(String customerName, String email, String address, double totalPrice, List<CartItem> items) {
        this.customerName = customerName;
        this.email = email;
        this.address = address;
        this.totalPrice = totalPrice;
        this.items = items;
    }

    // ✅ New constructor for use in controller (without items list)
    public Order(String customerName, String address, String email, double totalPrice) {
        this.customerName = customerName;
        this.address = address;
        this.email = email;
        this.totalPrice = totalPrice;
    }

    // Getters & Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public List<CartItem> getItems() { return items; }
    public void setItems(List<CartItem> items) { this.items = items; }
}
