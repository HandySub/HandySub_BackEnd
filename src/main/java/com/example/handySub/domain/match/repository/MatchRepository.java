package com.example.handySub.domain.match.repository;

import com.example.handySub.domain.match.collection.MatchCollections;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MatchRepository extends MongoRepository<MatchCollections, Long> {
}
