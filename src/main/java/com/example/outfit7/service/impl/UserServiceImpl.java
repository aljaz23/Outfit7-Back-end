package com.example.outfit7.service.impl;

import com.example.outfit7.model.User;
import com.example.outfit7.repository.UserRepository;
import com.example.outfit7.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean userExist(String userId) {
        boolean userExisting = userRepository.findById(userId).isPresent();
        return userExisting;
    }

    @Override
    public int getApiCalls(String userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return user.getApiCallsCount();
    }

    @Override
    public boolean customerSupportEnabled() {
        Calendar calendar = Calendar.getInstance();
        int currentHour = LocalTime.now().getHour();
        int currentDay = calendar.get(Calendar.DAY_OF_WEEK);

        if ((currentDay >= Calendar.MONDAY && currentDay <= Calendar.FRIDAY) && (9 <= currentHour && currentHour < 15)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User updateUserApiCall(String userId) {
        User user = userRepository.findById(userId).get();
        user.setApiCallsCount(user.getApiCallsCount() + 1);
        return userRepository.save(user);
    }

    @Override
    public boolean externalPartnerSupportsDevice(String param, String auth) {
        boolean supportsDevice = true;
        try {
            // Request URL
            String url = "https://us-central1-o7tools.cloudfunctions.net/fun7-ad-partner?countryCode=" + param;

            // Create Headers
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", auth);

            // Create Request
            HttpEntity request = new HttpEntity(headers);

            // Make Request
            ResponseEntity<String> response = new RestTemplate().exchange(url, HttpMethod.GET, request, String.class);

            // Get Json Response
            String json = response.getBody();

            if (json.contains("you shall not pass!")) {
                supportsDevice = false;
            } else {
                supportsDevice = true;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return supportsDevice;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserDetails(String userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.delete(userRepository.getReferenceById(userId));
    }

}
