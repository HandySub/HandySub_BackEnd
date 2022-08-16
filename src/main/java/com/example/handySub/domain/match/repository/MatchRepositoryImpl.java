package com.example.handySub.domain.match.repository;

import com.example.handySub.domain.match.collection.MatchCollections;
import com.example.handySub.domain.match.collection.QMatchCollections;
import com.example.handySub.domain.match.dto.QMatchDto_GetAllResponse;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.handySub.domain.match.collection.QMatchCollections.matchCollections;

//public class MatchRepositoryImpl implements MatchRepositoryCustom{
//
// 이거는 mysql 일 때,,,
//    private final JPAQueryFactory queryFactory;
//
//    public MatchRepositoryImpl(EntityManager em){
//        this.queryFactory=new JPAQueryFactory(em);
//    }
//
//    public List<MatchCollections> findAllMatchByHandicappedId(Long handicappedId){
//        return queryFactory
//                .selectFrom(matchCollections)
//                .where(matchCollections.handicappedId.eq(handicappedId))
//                .orderBy(
//                        matchCollections.startedAt.asc()
//                )
//                .fetch();
//    }
//}
