package com.example.handySub.domain.match.repository;

import com.example.handySub.domain.match.collection.MatchCollections;

import java.util.List;

public interface MatchRepositoryCustom {

    List<MatchCollections> findAllMatchByHandicappedId(Long handicappedId);
}
