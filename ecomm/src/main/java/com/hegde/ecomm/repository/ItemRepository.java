package com.hegde.ecomm.repository;

import com.hegde.ecomm.domain.Item;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends ReactiveCrudRepository<Item, String>, ReactiveMongoRepository<Item, String>{
}
