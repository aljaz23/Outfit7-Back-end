package com.example.outfit7.service;

import com.example.outfit7.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    boolean userExist(String userId);

    int getApiCalls(String userId);

    boolean customerSupportEnabled();

    User updateUserApiCall(String userId);

    boolean externalPartnerSupportsDevice(String param, String auth);

    List<User> getAllUsers();

    User getUserDetails(String userId);

    void deleteUser(String userId);

}
