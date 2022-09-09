package com.example.handySub.domain.user.repository;

import com.example.handySub.domain.user.collection.UserCollections;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends MongoRepository<UserCollections, String> {
  boolean existsByNickname(String nickname);
  UserCollections findByNickname(String nickname);
  void deleteByNickname(String nickname);

}
