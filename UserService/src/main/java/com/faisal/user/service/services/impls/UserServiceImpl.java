package com.faisal.user.service.services.impls;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.faisal.user.service.entities.User;
import com.faisal.user.service.exception.ResourceNotFoundException;
import com.faisal.user.service.repositories.UserRepository;
import com.faisal.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        String uniqueUserId = UUID.randomUUID().toString();
        user.setUserId(uniqueUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getSingleUser(String userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with given Id is not Available "));
    }

    @Override
    public String deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given Id is not Available "));
        userRepository.deleteById(userId);
        return "User Deleted...";
    }

    @Override
    public User updateUser(String userId, User user) {
        User u = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given Id is not Available "));
                u.setUserId(userId);
                u.setName(user.getName());
                u.setEmail(user.getEmail());
                u.setAbout(user.getAbout());

        return userRepository.save(u);
    }

    @Override
    public User partialUpdateUser(String userId, Map<String,Object> field) {
        User u = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given Id is not Available "));

        field.forEach((k,v)-> {
            switch(k){
                case "name":
                    u.setName((String)v);
                    break;
                case "email":
                    u.setEmail((String)v);
                case "about":
                    u.setAbout((String)v);
                default:
                    new ResourceNotFoundException("invalid field"+k);
            }
        });

        return userRepository.save(u);
    }
}
