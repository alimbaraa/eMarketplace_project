package com.example.Marketplace.service;

import com.example.Marketplace.dto.ItemDto;
import com.example.Marketplace.model.Item;
import com.example.Marketplace.repository.ItemRepository;
import com.example.Marketplace.request.AddItemRequest;
import com.example.Marketplace.util.ItemConvertor;
import com.example.Marketplace.util.ItemDtoCollection;
import com.example.Marketplace.util.RequestConvertor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private static final String PHOTO_PREFIX = "-photo";

    private final ItemRepository itemRepository;
    private final RequestConvertor requestConvertor;
    private final ItemConvertor itemConvertor;

    @Autowired
    public ItemService(ItemRepository itemRepository, RequestConvertor requestConvertor, ItemConvertor itemConvertor) {
        this.itemRepository = itemRepository;
        this.requestConvertor = requestConvertor;
        this.itemConvertor = itemConvertor;
    }

    public Item getItemById(Integer id){
        return itemRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<Item> getItems(){
        return itemRepository.findAll();
    }

    public ItemDtoCollection getItemsByPages(Integer pageNum, Integer size){
        Pageable pageable = PageRequest.of(pageNum, size);
        return itemConvertor.convertCollection(itemRepository.findAll(pageable).stream().toList());
    }

    public AddItemRequest addItem(AddItemRequest item){
        return requestConvertor.convert(itemRepository.save(itemConvertor.convertAddRequest(item)));
    }

    public Item updateItem(Item item){
        val oldItem = getItemById(item.getId());
        oldItem.setName(item.getName());
        oldItem.setPrice(item.getPrice());
        oldItem.setDescription(item.getDescription());
        oldItem.setSubmissionTime(item.getSubmissionTime());
        oldItem.setPhotoUrl(item.getPhotoUrl());
        return itemRepository.save(oldItem);
    }

    public ItemDto addPhotoToItem(String name) {
        val item = itemRepository.findByName(name);
        item.setPhotoUrl(name + PHOTO_PREFIX);
        return itemConvertor.convertItem(itemRepository.save(item));
    }

    public void deleteItemById(Integer id){
        itemRepository.deleteById(id);
    }
}
