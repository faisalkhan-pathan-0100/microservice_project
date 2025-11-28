package com.faisal.user.service.services.impls;

import java.util.*;
import java.util.stream.Collectors;


import com.faisal.user.service.entities.Hotel;
import com.faisal.user.service.entities.Rating;
import com.faisal.user.service.entities.User;
import com.faisal.user.service.exception.ResourceNotFoundException;
import com.faisal.user.service.repositories.UserRepository;
import com.faisal.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger logger =
            LoggerFactory.getLogger(UserServiceImpl.class);


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
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with given Id is not Available "));

        // fetch the rating given by particular user and for that we use rest template to call the RatingService end point which gives the rating by user
        Rating[] ratings =  restTemplate.getForObject("http://Rating-Service/ratings/users/"+user.getUserId(), Rating[].class);
//       logger.info("Ratings = {}", ratings);

        List<Rating> list = Arrays.asList(ratings);

        // to get the hotel detail below steps
        //step - 1 iterate over List<Rating>
         List<Rating> ratingList = list.stream().map(
                 (rating) ->{
                     //step - 2 extract the hotel id & //step -3 call the api end point of  HotelService which gives the Hotel details by hotel id
                     ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://Hotel-Service/hotels/"+rating.getHotelId(), Hotel.class);
                     Hotel hotel = forEntity.getBody();

                     //step -4 set the hotel details in rating
                     rating.setHotel(hotel);
                     return rating;
                 }
         ).collect(Collectors.toList());
        user.setRatings(ratingList);

       return user;
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
