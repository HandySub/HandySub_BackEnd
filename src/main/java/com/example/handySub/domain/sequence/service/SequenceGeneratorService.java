package com.example.handySub.domain.sequence.service;

import com.example.handySub.domain.sequence.collection.SequenceCollections;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;


@RequiredArgsConstructor
@Service
public class SequenceGeneratorService {
    @Autowired
    private MongoOperations mongoOperations;

    public Long generateSequence(String seqName) {
        Query query = new Query(Criteria.where("_id").is(seqName));
        Update update = new Update().inc("seq", 1);
        SequenceCollections counter = mongoOperations
                .findAndModify(query, update, options().returnNew(true).upsert(true), SequenceCollections.class);

        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

}