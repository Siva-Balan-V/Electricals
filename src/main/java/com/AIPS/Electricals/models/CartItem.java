package com.AIPS.Electricals.models;

import jakarta.persistence.*;

@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type; // Either 'product' or 'service'
    private double price;
    private int quantity;

    // Optional: store reference ID from Product/Service
    private Long referenceId;

    public CartItem() {}

    public CartItem(String name, String type, double price, int quantity, Long referenceId) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.referenceId = referenceId;
    }

    // Getters & Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public Long getReferenceId() { return referenceId; }
    public void setReferenceId(Long referenceId) { this.referenceId = referenceId; }
}
