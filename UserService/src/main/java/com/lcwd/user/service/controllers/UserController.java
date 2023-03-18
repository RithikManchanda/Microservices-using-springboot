package com.lcwd.user.service.controllers;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.services.UserServices;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServices userServices;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = userServices.saveUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    //int retryCount=1;
   // @RateLimiter(name = "userRateLimiterExtra",fallbackMethod = "ratingHotelFallback" )
    @GetMapping("/{userId}")
    //@CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
   // @Retry(name = "ratingHotelService",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        //logger.info("Retry count {}",retryCount);
       // retryCount++;
        User user1 = userServices.getUser(userId);

        return ResponseEntity.ok(user1);
    }

    //creating the fallback method here....
    public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex){
       //logger.info("Fallback is executed bcoz service is down", ex.getMessage());
        ex.printStackTrace();

       User user = User.builder()
               .email("dummy@gmail.com")
               .name("Dummy Is here")
               .about("a dummy value")
               .UserId("2346353")
               .build();

       return new ResponseEntity<>(user,HttpStatus.OK);
    }



    @GetMapping
   public ResponseEntity<List<User>> getAllUsers(){
        List<User> allUsers = userServices.getAllUser();
        return ResponseEntity.ok(allUsers);
   }


}
