package com.example.handySub.domain.user.repository;

import com.example.handySub.domain.user.dto.UserDto;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository implements UserRepositoryImpli {
    @Autowired
    MongoTemplate mongoTemplate;
    String COLLECTION_NAME = "user"; //컬렉션(테이블) 이름 : user

    @Override //중복 회원 방지 추가해야 됨.
    public void userInsert(UserDto userDto){
        mongoTemplate.insert(userDto, COLLECTION_NAME);
    }

    @Override
    public void userUpdate(UserDto userDto){

    }

    @Override
    public void userDelete(UserDto userDto){

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
