package com.hegde.ecomm.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private @Id String id;
    private List<CartItem> cartItems;
    private String userId;

    public Cart(String id, String userId) {
        this(id, new ArrayList<>(), userId);
    }
}
