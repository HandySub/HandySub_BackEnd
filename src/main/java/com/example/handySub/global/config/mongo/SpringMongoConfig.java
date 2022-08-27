package com.example.handySub.global.config.mongo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@Configuration
public class SpringMongoConfig {

    @Bean
   public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDatabaseFactory,
                                      MongoMappingContext context){
        MappingMongoConverter converter =
                new MappingMongoConverter(new DefaultDbRefResolver(mongoDatabaseFactory), context);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDatabaseFactory, converter);
        return mongoTemplate;
    }
}
