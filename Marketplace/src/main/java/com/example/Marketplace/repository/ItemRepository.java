package com.example.Marketplace.repository;

import com.example.Marketplace.dto.ItemDto;
import com.example.Marketplace.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    Item findByName(String name);
}
