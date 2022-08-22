package com.example.handySub.domain.match.repository;

import com.example.handySub.domain.match.collection.MatchCollections;

import java.util.List;
import java.util.Optional;

public interface MatchRepositoryCustom {

    Optional<MatchCollections> findNotDeletedByBoardId(String _id);

}
