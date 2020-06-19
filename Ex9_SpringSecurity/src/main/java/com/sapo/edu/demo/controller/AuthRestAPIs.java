package com.sapo.edu.demo.controller;

import com.sapo.edu.demo.dao.IRoleDao;
import com.sapo.edu.demo.dao.IUserDao;
import com.sapo.edu.demo.message.request.LoginForm;
import com.sapo.edu.demo.message.request.SignUpForm;
import com.sapo.edu.demo.message.response.JwtResponse;
import com.sapo.edu.demo.model.Role;
import com.sapo.edu.demo.model.RoleName;
import com.sapo.edu.demo.model.User;
import com.sapo.edu.demo.security.jwt.JwtProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthRestAPIs {

  @Autowired AuthenticationManager authenticationManager;

  @Autowired IUserDao userDao;

  @Autowired IRoleDao roleDao;

  @Autowired PasswordEncoder encoder;

  @Autowired JwtProvider jwtProvider;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = jwtProvider.generateJwtToken(authentication);
    return ResponseEntity.ok(new JwtResponse(jwt));
  }

  @PostMapping("/signup")
  public ResponseEntity<String> registerUser(
      @NotNull @Valid @RequestBody SignUpForm signUpRequest) {
    if (userDao.existsByUsername(signUpRequest.getUsername())) {
      return new ResponseEntity<String>(
          "Fail -> Username is already taken!", HttpStatus.BAD_REQUEST);
    }

    // Creating user's account
    User user = new User(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    strRoles.forEach(
        role -> {
          switch (role) {
            case "Full":
              Role adminRole =
                  roleDao
                      .findByName(RoleName.ROLE_Full)
                      .orElseThrow(
                          () -> new RuntimeException("Fail! -> Cause: User Role not find."));
              roles.add(adminRole);

              break;

            case "Read_Only":
              Role userRole =
                  roleDao
                      .findByName(RoleName.ROLE_Read_Only)
                      .orElseThrow(
                          () -> new RuntimeException("Fail! -> Cause: User Role not find."));
              roles.add(userRole);
              break;
          }
        });

    user.setRoles(roles);
    userDao.save(user);

    return ResponseEntity.ok().body("User registered successfully!");
  }
}
