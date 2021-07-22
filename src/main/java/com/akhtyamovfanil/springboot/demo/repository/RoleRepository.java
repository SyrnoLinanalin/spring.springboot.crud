package com.akhtyamovfanil.springboot.demo.repository;

import com.akhtyamovfanil.springboot.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, CustomRole<Role> {
    Role findByName(String name);

    @Override
    Set<Role> getRoleSet();
    @Override
    Role getRoleByName(String rolename);

}



