package com.lcwd.user.service.services.impl;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.external.services.HotelService;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserServices {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private HotelService hotelService;
    @Override
    public User saveUser(User user) {

        //generate unique user id......
        String randomUserId  = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User with given id is not found on server" + userId));

        Rating[] ratingForUser  = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
        logger.info("{}",ratingForUser);


       List<Rating> ratings =  Arrays.stream(ratingForUser).collect(Collectors.toList());

        List<Rating> ratingWithHotelDetails = ratings.stream().map(rating -> {

            //ResponseEntity<Hotel> hotelEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());//hotelEntity.getBody();

            //logger.info("response status code : {}",hotelEntity.getStatusCode());

            rating.setHotel(hotel);
            return rating;

        }).collect(Collectors.toList());

        user.setRatings(ratingWithHotelDetails);



        return user;
        //to get rating for this user....
        //http://localhost:8083/ratings/users/ffa7fa4f-d28f-4058-9047-0237ca2b363f
    }
}
