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
    private RoleService roleService;



    public Optional<User> findUserByUsername(String username){
        return userRepository.getUserByUsername(username);

    }


    public List<User> allUsers(){
        return  userRepository.findAll();
    }

    public void save(User user, String[] name) {
        user.setPassword(user.getPassword());
       // user.setRoles(roleService.getRoleSetForUser(name));
        userRepository.save(user);
    }

    public User getById(Long id) {
     /*   User user = null;
        Optional<User> optional = userRepository.findById(id);
        if(optional.isPresent()){
            user = optional.get();
        }
        return user;*/
        return userRepository.findById(id).get();
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
