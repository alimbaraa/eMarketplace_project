package com.example.Marketplace.util;

import com.example.Marketplace.dto.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDtoCollection {
    private List<ItemDto> collection;
}
