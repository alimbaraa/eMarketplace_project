package com.example.Marketplace.controllers;

import com.example.Marketplace.service.ItemService;
import com.example.Marketplace.service.PhotoService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/photo")
public class PhotoController {
    private ItemService itemService;
    private PhotoService photoService;

    @Autowired
    public PhotoController(ItemService itemService, PhotoService photoService) {
        this.itemService = itemService;
        this.photoService = photoService;
    }

    @PostMapping
    public ResponseEntity<?> uploadPhoto(@RequestParam("photo") MultipartFile photo, @RequestParam("name") String name){
        try {
            val itemDto = itemService.addPhotoToItem(name);
            photoService.storePhoto(photo, itemDto.getPhotoUrl());
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity<?>  getAllPhotos(){
        try {
            val photos = photoService.getAllPhotos();
            return ResponseEntity.ok(photos);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
