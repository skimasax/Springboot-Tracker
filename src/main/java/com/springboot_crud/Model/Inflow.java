package com.springboot_crud.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.springboot_crud.ENUM.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "inflows")
public class Inflow {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @Column(name = "description")
    private String description;

    @Column(name="quantity")
    private Integer quantity;

    private Double price;

    @Column(name = "total_price")
    private Double totalPrice;


    public Inflow(Long id, String description, User user, Integer quantity, Double price, Double totalPrice) {
        this.id = id;
        this.description = description;
        this.user = user;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public Inflow() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

}
