package com.hegde.ecomm.controller;

import com.hegde.ecomm.domain.Item;
import com.hegde.ecomm.json.ItemRequest;
import com.hegde.ecomm.service.ItemService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v0/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public Mono<Item> addItem(@RequestBody ItemRequest itemRequest){
        return itemService.addItem(itemRequest);
    }

    @GetMapping
    public Flux<Item> searchItems(@RequestParam(value = "name", required = false) String name){
        return itemService.searchItem(name);
    }
}
