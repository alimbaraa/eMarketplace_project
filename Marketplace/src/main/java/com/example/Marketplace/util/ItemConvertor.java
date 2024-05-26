package com.example.Marketplace.util;

import com.example.Marketplace.dto.ItemDto;
import com.example.Marketplace.model.Item;
import com.example.Marketplace.request.AddItemRequest;
import com.example.Marketplace.request.UpdateItemRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemConvertor {
    public ItemDto convertItem(Item item){
        return ItemDto.builder()
                .name(item.getName())
                .price(item.getPrice())
                .description(item.getDescription())
                .submissionTime(item.getSubmissionTime())
                .photoUrl(item.getPhotoUrl())
                .build();
    }

    public ItemDtoCollection convertCollection(List<Item> items){
        return ItemDtoCollection.builder()
                .collection(items.stream().map(this::convertItem).toList())
                .build();
    }

    public Item convertAddRequest(AddItemRequest request){
        return Item.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .submissionTime(request.getSubmissionTime())
                .photoUrl(request.getPhotoUrl())
                .build();
    }

    public Item convertUpdateRequest(UpdateItemRequest request){
        return Item.builder()
                .id(request.getId())
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .submissionTime(request.getSubmissionTime())
                .photoUrl(request.getPhotoUrl())
                .build();
    }

}
