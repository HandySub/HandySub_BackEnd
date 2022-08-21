package com.example.handySub.domain.match.repository;

import com.example.handySub.domain.match.collection.StationCollections;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends  MongoRepository<StationCollections, String> {

}
