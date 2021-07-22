package com.akhtyamovfanil.springboot.demo.repository;

import com.akhtyamovfanil.springboot.demo.model.Role;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;

public class CustomRoleImpl implements CustomRole{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Set<Role> getRoleSet() {
        try{
            return new HashSet<>(entityManager.createQuery("SELECT r FROM Role r", Role.class)
                    .getResultList());
        } catch (NoResultException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Role getRoleByName(String rolename) {
        return entityManager.createQuery("SELECT  r FROM Role r  where r.name  = :name", Role.class)
                .setParameter("name", rolename)
                .getSingleResult();
    }
}
