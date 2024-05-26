package com.example.Marketplace.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "item")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "description")
    private String description;

    @Column(name = "submission_time")
    private Instant submissionTime;

    @Column(name = "photoURl")
    private String photoUrl;

    public Item(String name, double price, String description, Instant submissionTime, String photoUrl) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.submissionTime = submissionTime;
        this.photoUrl = photoUrl;
    }
}
