package com.hegde.ecomm.controller;

import com.hegde.ecomm.domain.Cart;
import com.hegde.ecomm.service.CartService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v0/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("{id}")
    Mono<Cart> addItemToCart(@RequestHeader("X-USER-ID") String userId,
                             @PathVariable String id){
        return cartService.addItemToCart(id, userId);
    }
}
