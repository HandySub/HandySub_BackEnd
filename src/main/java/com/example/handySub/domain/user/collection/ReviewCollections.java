package com.example.handySub.domain.user.collection;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.sql.Date;

@Document(collection = "reviews")
@Getter
@Setter
@NoArgsConstructor
public class ReviewCollections {
    @Id
    private ObjectId reviewId;

    private ObjectId handicapped;

    private ObjectId nonHandicapped;

    private Double cookies;

    private String comment;

    private Date createdAt;

    @Builder
    public ReviewCollections(ObjectId handicapped, ObjectId nonHandicapped, Double cookies,
                            String comment, Date createdAt){
        this.handicapped = handicapped;
        this.nonHandicapped = nonHandicapped;
        this.cookies = cookies;
        this.comment = comment;
        this.createdAt = createdAt;
    }
}
