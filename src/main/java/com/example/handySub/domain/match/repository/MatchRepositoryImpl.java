package com.example.handySub.domain.match.repository;


import com.example.handySub.domain.match.collection.MatchCollections;
import com.example.handySub.domain.match.collection.QMatchCollections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.mongodb.morphia.MorphiaQuery;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import java.util.Optional;

import static com.example.handySub.domain.match.collection.QMatchCollections.matchCollections;

public class MatchRepositoryImpl implements MatchRepositoryCustom {


   Morphia morphia;
   Datastore datastore;
    @Override
    public Optional<MatchCollections> findNotDeletedByBoardId(String _id) {
        QMatchCollections matchCollections = new QMatchCollections("matchCollections");
        MorphiaQuery<MatchCollections> query = new MorphiaQuery<MatchCollections>(morphia, datastore, matchCollections);
        return Optional.ofNullable(query
                .where(matchCollections._id.eq(_id))
                .fetchFirst());
    }

    private BooleanExpression isDeletedCheck() {
        return matchCollections.isDeleted.eq(false);
    }

//   @Override
//   public List<MatchCollections> findAllMatchByHandicappedId(Long handicappedId) {
//       QMatchCollections matchCollections = new QMatchCollections("matchCollections");
//       MorphiaQuery<MatchCollections> query = new MorphiaQuery<MatchCollections>(morphia, datastore, matchCollections);
//       return query
//               .where(matchCollections._id.eq(handicappedId))
//               .fetch();
//   }
//
}
