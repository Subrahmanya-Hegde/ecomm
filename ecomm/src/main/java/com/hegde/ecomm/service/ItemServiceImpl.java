package com.hegde.ecomm.service;

import com.hegde.ecomm.domain.Item;
import com.hegde.ecomm.json.ItemRequest;
import com.hegde.ecomm.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public Mono<Item> addItem(ItemRequest itemRequest) {
        return itemRepository.findById(itemRequest.getName())
                .flatMap(existingItem -> Mono.error(new BadRequestException("Item Already Exists")))
                .switchIfEmpty(Mono.defer(() -> itemRepository.save(new Item(itemRequest.getName(), itemRequest.getPrice()))))
                .cast(Item.class);
    }

    @Override
    public Flux<Item> getItems() {
        return itemRepository.findAll();
    }
}
