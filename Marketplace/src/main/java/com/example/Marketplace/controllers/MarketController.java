package com.example.Marketplace.controllers;


import com.example.Marketplace.dto.ItemDto;
import com.example.Marketplace.request.AddItemRequest;
import com.example.Marketplace.request.UpdateItemRequest;
import com.example.Marketplace.service.ItemService;
import com.example.Marketplace.util.ItemConvertor;
import com.example.Marketplace.util.ResponseConvertor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;

@RestController
@RequestMapping("/market")
public class MarketController {
    private final ItemService itemService;
    private final ItemConvertor itemConvertor;
    private final ResponseConvertor responseConvertor;


    @Autowired
    public MarketController(ItemService itemService, ItemConvertor itemConvertor, ResponseConvertor responseConvertor) {
        this.itemService = itemService;
        this.itemConvertor = itemConvertor;
        this.responseConvertor = responseConvertor;
    }

    @GetMapping("/")
    public ModelAndView homePage(){
        return new ModelAndView("forward:/index.html");
    }

    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<?> getItem(@PathVariable("id") Integer itemId){
        try {
            val itemDto = itemConvertor.convertItem(itemService.getItemById(itemId));
            return ResponseEntity.status(HttpStatus.OK).body(itemDto);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @ResponseBody
    @GetMapping("/all")
    public ResponseEntity<?> getAllItems(){
        try{
            val dtoCollection = itemConvertor.convertCollection(itemService.getItems());
            return ResponseEntity.status(HttpStatus.OK).body(dtoCollection);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @ResponseBody
    @GetMapping("/pages")
    public ResponseEntity<?> getItemsByPages(@RequestParam("pageNum") Integer num, @RequestParam("pageSize") Integer size){
        try {
            val dtoCollection = itemService.getItemsByPages(num, size);
            return ResponseEntity.status(HttpStatus.OK).body(dtoCollection);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @ResponseBody
    @PostMapping("/add")
    public ResponseEntity<?> addItem(@RequestBody AddItemRequest newItem){
        try {
            newItem.setSubmissionTime(Instant.now());
            val response = responseConvertor.convertAddResponse(itemConvertor.convertAddRequest(itemService.addItem(newItem)));
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @ResponseBody
    @PutMapping("/update")
    public ResponseEntity<?> updateItem(@RequestBody UpdateItemRequest request){
        try {
            request.setSubmissionTime(Instant.now());
            val response = responseConvertor.convertUpdateResponse(itemService.updateItem(itemConvertor.convertUpdateRequest(request)));
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("could not update item");
        }
    }


    @ResponseBody
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteItemById(@PathVariable("id") Integer id){
        try {
            itemService.deleteItemById(id);
            return ResponseEntity.status(HttpStatus.GONE).body("item deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("could not delete");
        }
    }
}
