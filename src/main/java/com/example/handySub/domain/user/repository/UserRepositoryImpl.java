package com.example.handySub.domain.user.repository;

import com.example.handySub.domain.user.collection.UserCollections;
import com.example.handySub.domain.user.dto.UserDto;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository { // -->  public class UserRepositoryImpl implements JpaRepository<User, String>
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private UserCollectionRepo userCollectionRepo;

    String COLLECTION_NAME = "users"; //컬렉션(테이블) 이름 : user

    @Override
    public void userInsert(UserDto userDto){
        UserCollections entity = userDto.toEntity();
        userCollectionRepo.save(entity);
    }

    @Override
    public void userInfoUpdate(String email, String newinfo){
        Query query = new Query(new Criteria("email").is(email)); //값 찾기
        Update update = new Update();
        update.set("info",newinfo);
        mongoTemplate.updateFirst(query,update,UserDto.class); //수정
    }

    @Override
    public void userDelete(String email){
        Query query = new Query(new Criteria("email").is(email));
        mongoTemplate.remove(query);
    }

    @Override
    public UserDto loginCheck(String email, String pwd){
        //우리는 아이디가 이메일이므로 email로 로그인 확인함
        Query query = new Query(new Criteria("email").is(email).and("password").is(pwd));
        List<UserDto> member_check = mongoTemplate.find(query, UserDto.class, COLLECTION_NAME);
        if(member_check.size()>0) { //회원가입이 되어 있는지 확인
            return member_check.get(0);
        }else{
            return null;
        }
    }
}
