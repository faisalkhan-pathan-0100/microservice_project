package com.faisal.hotel.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/staffs")
public class StaffController {

//    List<String> staffs = new ArrayList<>();
//        staffs.

    @GetMapping
    public List<String> allStaff(){
        return Arrays.asList("staff-1","staff-2","staff-3");
    }
}
