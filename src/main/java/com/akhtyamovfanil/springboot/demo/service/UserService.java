package com.akhtyamovfanil.springboot.demo.service;

import com.akhtyamovfanil.springboot.demo.model.Role;
import com.akhtyamovfanil.springboot.demo.model.User;
import com.akhtyamovfanil.springboot.demo.repository.RoleRepository;
import com.akhtyamovfanil.springboot.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;



    public Optional<User> findUserByUsername(String username){
        return userRepository.getUserByUsername(username);

    }


    public List<User> allUsers(){
        return  userRepository.findAll();
    }

    public void save(User user, String[] roles) {
        user.setRoles(roleService.getRoleSetForUser(roles));
        userRepository.save(user);
    }

    public User getById(Long id) {
        return userRepository.findById(id).get();
    }

    public void delete(User user) {
        userRepository.deleteById(user.getId());
    }
}
