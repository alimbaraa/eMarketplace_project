package com.example.Marketplace.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
public class UpdateItemResponse {
    private String name;

    private double price;

    private String description;

    private Instant submissionTime;

    private String photoUrl;
}
