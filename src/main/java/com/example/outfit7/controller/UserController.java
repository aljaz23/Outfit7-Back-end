package com.example.outfit7.controller;

import com.example.outfit7.exception.ResourceException;
import com.example.outfit7.model.EnabledServices;
import com.example.outfit7.model.User;
import com.example.outfit7.service.impl.UserServiceImpl;
import com.neovisionaries.i18n.CountryCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@RestController
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    //CHECK SERVICES API
    @PostMapping("/user")
    public ResponseEntity checkServices(@RequestParam("timeZone") String timeZone, @RequestParam("userId") String userId, @RequestParam("countryCode") String countryCode, @RequestHeader("Authorization") String authorization) {

        //WE CHECK IF ALL PARAMS ARE PROVIDED
        if (timeZone.equals("") || userId.equals("") || countryCode.equals("")) {
            throw new ResourceException(HttpStatus.BAD_REQUEST, "Params Are Mandatory");
        }

        //WE CHECK IF USER CREDENTIALS ARE VALID
        if (!authorization.equals("Basic ZnVuN3VzZXI6ZnVuN3Bhc3M=")) {
            throw new ResourceException(HttpStatus.UNAUTHORIZED, "Invalid User Credentials");
        }

        //WE CHECK FOR VALID COUNTRY CODE OF USER
        CountryCode cc = CountryCode.getByCode(countryCode);
        if (cc == null) {
            throw new ResourceException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("'%s' Is Not Valid Country Code", countryCode));
        }

        //WE CHECK FOR VALID TIMEZONE OF USER
        List<String> timeZoneList = List.of(TimeZone.getAvailableIDs());
        if (!timeZoneList.contains(timeZone)) {
            throw new ResourceException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("'%s' Is Not Valid Time Zone", timeZone));
        }

        boolean partnerSupportsDevice = userServiceImpl.externalPartnerSupportsDevice(countryCode, authorization);
        boolean customerSupportEnabled = userServiceImpl.customerSupportEnabled();

        int apiCalls = 0;

        // WE CHECK IF USER ALREADY EXIST IN DATABASE AND WE UPDATE API CALLS
        if (userServiceImpl.userExist(userId)) {
            userServiceImpl.updateUserApiCall(userId);
            apiCalls = userServiceImpl.getApiCalls(userId);
        }
        //IF DOESN'T EXIST WE FIRST SAVE IT
        else {
            userServiceImpl.saveUser(new User(timeZone, userId, countryCode));
        }
        return new ResponseEntity(
                //IN ENABLED SERVICES CONSTRUCTOR ARE CONDITIONS FOR RETURN
                new EnabledServices(customerSupportEnabled, apiCalls, partnerSupportsDevice), HttpStatus.OK);
    }

    //ADMIN API
    @GetMapping("/user_list")
    public ResponseEntity getUserList() {
        List<User> listUsersAllInformation = userServiceImpl.getAllUsers();
        List<String> listUserIds = new ArrayList<>();

        for (User user : listUsersAllInformation) {
            listUserIds.add(user.getUserId());
        }
        return new ResponseEntity(listUserIds, HttpStatus.OK);
    }

    //ADMIN API
    @GetMapping("/user_list/{userid}")
    public ResponseEntity getUserDetails(@PathVariable String userid) {
        boolean userExist = userServiceImpl.userExist(userid);
        if (userExist && !userid.equals("")) {
            User user = userServiceImpl.getUserDetails(userid);
            return new ResponseEntity(user, HttpStatus.OK);
        } else {
            throw new ResourceException(HttpStatus.NOT_FOUND, String.format("User '%s' Not Found", userid));
        }
    }

    //ADMIN API
    @DeleteMapping("/user_list/{userid}")
    public ResponseEntity deleteUser(@PathVariable String userid) {
        boolean userExist = userServiceImpl.userExist(userid);
        if (userExist) {
            userServiceImpl.deleteUser(userid);
            return new ResponseEntity(String.format("User '%s' Was Successfully Deleted", userid), HttpStatus.OK);
        } else {
            throw new ResourceException(HttpStatus.NOT_FOUND, String.format("User '%s' Not Found", userid));
        }
    }
}
