package com.hegde.ecomm.service;

import com.hegde.ecomm.domain.Cart;
import com.hegde.ecomm.domain.CartItem;
import com.hegde.ecomm.repository.CartRepository;
import com.hegde.ecomm.repository.ItemRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;

    public CartServiceImpl(CartRepository cartRepository, ItemRepository itemRepository) {
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public Mono<Cart> addItemToCart(String id, String userId) {
        return cartRepository.findById(userId)
                .defaultIfEmpty(new Cart(userId, new ArrayList<>(), userId))
                .flatMap(cart -> {
                    Optional<CartItem> existingItem = cart.getCartItems().stream()
                            .filter(cartItem -> cartItem.getItem().getId().equalsIgnoreCase(id)).findFirst();
                    if (existingItem.isPresent()) {
                        existingItem.get().increment();
                        return Mono.just(cart);
                    } else {
                        return itemRepository.findById(id)
                                .switchIfEmpty(Mono.error(new BadRequestException("Item Not found")))
                                .flatMap(item -> {
                                    CartItem cartItem = new CartItem(item);
                                    cart.getCartItems().add(cartItem);
                                    return cartRepository.save(cart);
                                });
                    }
                });
    }
}
