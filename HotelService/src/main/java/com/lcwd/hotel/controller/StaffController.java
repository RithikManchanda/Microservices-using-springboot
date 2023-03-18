package com.lcwd.hotel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {


    @GetMapping
    public ResponseEntity<List<String>> getStaffDetails(){
        List<String> list = new ArrayList<>();
        list.add("Ramm");
        list.add("Gopal");
        list.add("sharma");

        return ResponseEntity.ok(list);
    }

}
