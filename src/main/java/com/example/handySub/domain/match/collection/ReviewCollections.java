package com.example.handySub.domain.match.collection;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "station")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ReviewCollections {

    private double cookies;
    private String comment;
    private Long createdAt;

    @Builder
    public ReviewCollections(double cookies, String comment, Long createdAt){
        this.cookies=cookies;
        this.comment=comment;
        this.createdAt=createdAt;
    }
}
