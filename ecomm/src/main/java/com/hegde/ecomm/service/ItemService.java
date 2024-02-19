package com.hegde.ecomm.service;

import com.hegde.ecomm.domain.Item;
import com.hegde.ecomm.json.ItemRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ItemService {

    Mono<Item> addItem(ItemRequest itemRequest);
    Flux<Item> getItems();

}
