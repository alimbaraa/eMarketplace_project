package com.example.Marketplace.util;

import com.example.Marketplace.model.Item;
import com.example.Marketplace.request.AddItemRequest;
import org.springframework.stereotype.Service;

@Service
public class RequestConvertor {
    public AddItemRequest convert(Item item){
        return AddItemRequest.builder()
                .name(item.getName())
                .price(item.getPrice())
                .description(item.getDescription())
                .submissionTime(item.getSubmissionTime())
                .photoUrl(item.getPhotoUrl())
                .build();
    }

}
