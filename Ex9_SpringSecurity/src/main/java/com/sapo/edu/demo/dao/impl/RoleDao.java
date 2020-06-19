package com.sapo.edu.demo.dao.impl;

import com.sapo.edu.demo.dao.IRoleDao;
import com.sapo.edu.demo.mapper.RoleRowMapper;
import com.sapo.edu.demo.model.Role;
import com.sapo.edu.demo.model.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleDao implements IRoleDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public Optional<Role> findByName(RoleName roleName) {
        System.out.println(roleName.toString());
    return Optional.ofNullable(
        jdbcTemplate.queryForObject(
            "select * from permission where name_per=?", new RoleRowMapper(), roleName.toString()));
    }
}
