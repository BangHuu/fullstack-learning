package com.sapo.edu.demo.security.services;

import com.sapo.edu.demo.dao.IUserDao;
import com.sapo.edu.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired IUserDao userDao;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user =
        userDao
            .findByUsername(username)
            .orElseThrow(
                () ->
                    new UsernameNotFoundException("User Not Found with -> username : " + username));

    return UserPrinciple.build(user);
  }
}
