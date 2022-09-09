package com.example.handySub.global.jwt.security;

import com.example.handySub.domain.user.collection.UserCollections;
import com.example.handySub.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.example.handySub.domain.user.collection.UserRole;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetails implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
    final UserCollections userCollections = userRepository.findByNickname(nickname);

    if (userCollections == null) {
      throw new UsernameNotFoundException("User '" + nickname + "' not found");
    }

    return org.springframework.security.core.userdetails.User//
        .withUsername(nickname)//
        .password(userCollections.getPassword())//
        .authorities(userCollections.getUserRoles())//
        .accountExpired(false)//
        .accountLocked(false)//
        .credentialsExpired(false)//
        .disabled(false)//
        .build();
  }

}
