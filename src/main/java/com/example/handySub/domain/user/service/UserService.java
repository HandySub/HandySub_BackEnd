package com.example.handySub.domain.user.service;

import com.example.handySub.domain.match.collection.MatchCollections;
import com.example.handySub.domain.match.collection.StationCollections;
import com.example.handySub.domain.user.collection.UserRole;
import com.example.handySub.domain.user.repository.UserRepository;
import com.example.handySub.global.jwt.exception.CustomException;
import com.example.handySub.global.jwt.security.JwtTokenProvider;
import com.example.handySub.domain.user.collection.UserCollections;

import com.example.handySub.global.sequence.service.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public void test(){
        UserCollections userCollections = new UserCollections();
        Long temp = sequenceGeneratorService.generateSequence(userCollections.SEQUENCE_NAME);
        userCollections.setUserId(Long.toString(temp));
        userCollections.setEmail("admin@email.com");
        userCollections.setNickname("admin");
        userCollections.setPassword("abcdefgh1234");
        userCollections.setSex(true);
        userCollections.setHandicapped(true);
        userCollections.setInfo("설명설명");
        userCollections.setCookies(3.0);
        userCollections.setUserRoles(new ArrayList<UserRole>(Arrays.asList(UserRole.ROLE_ADMIN)));
        System.out.println(userCollections.toString());
        signup(userCollections);
    }

    public String login(String nickname, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(nickname, password));
            return jwtTokenProvider.createToken(nickname, userRepository.findByNickname(nickname).getUserRoles());
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String signup(UserCollections userCollections) {
        if (!userRepository.existsByNickname(userCollections.getNickname())) {
            userCollections.setPassword(passwordEncoder.encode(userCollections.getPassword()));
            userRepository.save(userCollections);
            return jwtTokenProvider.createToken(userCollections.getNickname(), userCollections.getUserRoles());
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public void delete(String nickname) {
        userRepository.deleteByNickname(nickname);
    }

    public UserCollections search(String nickname) {
        UserCollections userCollections = userRepository.findByNickname(nickname);
        if (userCollections == null) {
            throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
        }
        return userCollections;
    }

    public UserCollections whoami(HttpServletRequest req) {
        return userRepository.findByNickname(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
    }

    public String refresh(String nickname) {
        return jwtTokenProvider.createToken(nickname, userRepository.findByNickname(nickname).getUserRoles());
    }

}
