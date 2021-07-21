package com.akhtyamovfanil.springboot.demo.service;

import com.akhtyamovfanil.springboot.demo.model.Role;
import com.akhtyamovfanil.springboot.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;
    private  EntityManager entityManager;


    public void save(Role role) {
        Role managed = entityManager.merge(role);
        entityManager.persist(managed);
    }


    public void delete(Role role) {
        Role managed = entityManager.merge(role);
        entityManager.remove(managed);
    }


    public Role getById(Long id) {
        return entityManager.find(Role.class, id );
    }


    public Role getRoleByName(String rolename) {

        return entityManager.createQuery("SELECT  r FROM Role r  where r.name  = :name", Role.class)
                .setParameter("name", rolename)
                .getSingleResult();

    }


    public List<Role> getRoleSet() {
        return  roleRepository.findAllByName();
     /*   try{
            return new HashSet<>(entityManager.createQuery("SELECT r FROM Role r", Role.class)
                    .getResultList());
        } catch (NoResultException e){
            e.printStackTrace();
            return null;
        }*/
    }
/*
    public List<Role> getRoleSetForUser(String[] rolenames) {
        List<Role> rolesSet = new HashSet<>();
        for (Role role : getRoleSet()) {
            for (String st : rolenames) {
                if (st.equals(role.getName())) {
                    rolesSet.add(getRoleByName(role.getName()));
                }
            }
        }
        return rolesSet;
    }*/

}
