package com.akhtyamovfanil.springboot.demo.repository;

import com.akhtyamovfanil.springboot.demo.model.Role;

import java.util.Set;

public interface CustomRole<T> {

    public Set<Role> getRoleSet();
    public Role getRoleByName(String rolename);

}
