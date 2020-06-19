package com.sapo.edu.demo.dao;


import com.sapo.edu.demo.model.Role;
import com.sapo.edu.demo.model.RoleName;


import java.util.Optional;


public interface IRoleDao {
    Optional<Role> findByName(RoleName roleName);
}