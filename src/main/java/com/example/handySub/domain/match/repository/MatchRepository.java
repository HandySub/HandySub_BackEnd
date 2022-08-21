package com.example.handySub.domain.match.repository;

import com.example.handySub.domain.match.collection.MatchCollections;

import com.example.handySub.domain.match.collection.StationCollections;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends MongoRepository<MatchCollections, String>, MatchRepositoryCustom{
    List<MatchCollections> findAllByStartStation(StationCollections startStation);
    List<MatchCollections> findAllByFinishStation(StationCollections finishStation);
    MatchCollections findBy_id(String _id);
    List<MatchCollections> findAllByHandicappedId(Long handicappedId);
}
