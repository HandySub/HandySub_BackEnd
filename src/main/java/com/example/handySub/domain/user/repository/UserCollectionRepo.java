package com.example.handySub.domain.user.repository;

import com.example.handySub.domain.user.collection.UserCollections;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserCollectionRepo extends MongoRepository<UserCollections, String> {
}
