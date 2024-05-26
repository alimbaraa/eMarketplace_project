package com.example.Marketplace.util;

import com.example.Marketplace.model.Item;
import com.example.Marketplace.response.AddItemResponse;
import com.example.Marketplace.response.UpdateItemResponse;
import org.springframework.stereotype.Service;

@Service
public final class ResponseConvertor {
    public AddItemResponse convertAddResponse(Item item){
        return AddItemResponse.builder()
                .name(item.getName())
                .price(item.getPrice())
                .description(item.getDescription())
                .submissionTime(item.getSubmissionTime())
                .photoUrl(item.getPhotoUrl())
                .build();
    }

    public UpdateItemResponse convertUpdateResponse(Item item){
        return UpdateItemResponse.builder()
                .name(item.getName())
                .price(item.getPrice())
                .description(item.getDescription())
                .submissionTime(item.getSubmissionTime())
                .photoUrl(item.getPhotoUrl())
                .build();
    }
}
