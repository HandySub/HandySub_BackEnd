package com.example.handySub.domain.user.service;

import com.example.handySub.domain.user.collection.DatabaseSeq;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@RequiredArgsConstructor
@Service
public class SequenceGeneratorService {
    private final MongoOperations mongoOperations;

    public long generateSeq(String seq){
        DatabaseSeq cnt = mongoOperations.findAndModify(query(where("_id").is(seq)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSeq.class);
        return !Objects.isNull(cnt) ? cnt.getSeq() : 1;
    }
}
