package com.akhtyamovfanil.springboot.demo.repository;

import com.akhtyamovfanil.springboot.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName (String name);
    Role getRoleByName(String rolename);
    @Query("SELECT r FROM Role r")
    List<Role> findAllByName();
    //Role getRoleByName(String[] name);
}
