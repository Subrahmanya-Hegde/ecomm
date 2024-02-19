package com.hegde.ecomm.service;

import com.hegde.ecomm.domain.Cart;
import reactor.core.publisher.Mono;

public interface CartService {

    Mono<Cart> addItemToCart(String id, String userId);
}
