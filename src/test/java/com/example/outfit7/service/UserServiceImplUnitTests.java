package com.example.outfit7.service;

import com.example.outfit7.model.EnabledServices;
import com.example.outfit7.model.User;
import com.example.outfit7.repository.UserRepository;
import com.example.outfit7.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplUnitTests {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    void Should_Update_User_Api_Call_Count() {
        User user = new User("Europe/Ljubljana", "aljaz123", "SL");

        when(userRepository.findById("aljaz123")).thenReturn(Optional.of(user));
        when(userRepository.save(any())).then(returnsFirstArg());

        User userApiCall = userServiceImpl.updateUserApiCall("aljaz123");
        assertThat(userApiCall.getApiCallsCount()).isEqualTo(2);

    }

    @Test
    void Should_Get_User_Details() {
        User user = new User("Europe/Ljubljana", "aljaz123", "SL");
        when(userRepository.findById("aljaz123")).thenReturn(Optional.of(user));

        User userDetails = userServiceImpl.getUserDetails("aljaz123");
        assertThat(user).isEqualTo(userDetails);
    }

    @Test
    void Should_Delete_User() {
        User user = new User("Europe/Ljubljana", "aljaz123", "SL");
        userServiceImpl.saveUser(user);
        userServiceImpl.deleteUser("aljaz123");

        boolean userPresent = userServiceImpl.userExist("aljaz123");
        assertThat(userPresent).isFalse();
    }

    @Test
    void Should_Check_If_Customer_Support_Should_Be_Enabled() {
        boolean isEnabledActual = userServiceImpl.customerSupportEnabled();
        Calendar calendar = Calendar.getInstance();
        int currHour = LocalTime.now().getHour();
        int currDay = calendar.get(Calendar.DAY_OF_WEEK);

        boolean isEnabledExpected = (currDay >= 2 && currDay <= 6) && (9 <= currHour && currHour < 15);
        assertThat(isEnabledActual).isEqualTo(isEnabledExpected);
    }

    @Test
    void Should_Save_User() {
        User user = new User("Europe/Ljubljana", "aljaz123", "SL");
        userServiceImpl.saveUser(user);
        assertThat(user).isNotNull();
    }

    @Test
    void Should_Get_List_Of_Users() {
        User user = new User("Europe/Ljubljana", "aljaz123", "SL");
        User user1 = new User("Europe/Ljubljana", "Harry", "UK");
        List<User> expectedList = Arrays.asList(user, user1);
        userRepository.saveAll(expectedList);

        when(userRepository.findAll()).thenReturn(expectedList);
        List<User> actualList = userServiceImpl.getAllUsers();

        assertThat(actualList.size()).isEqualTo(expectedList.size());
    }

    @Test
    void Should_Get_Enabled_Services() {
        EnabledServices enabledServices = new EnabledServices(false, 3, true);
        EnabledServices enabledServices1 = new EnabledServices(true, 6, false);

        assertThat(enabledServices.toString()).isEqualTo("disabled" + "enabled" + "disabled");
        assertThat(enabledServices1.getMultiplayer()).isEqualTo("enabled");
        assertThat(enabledServices1.getAds()).isEqualTo("disabled");
        assertThat(enabledServices1.getUserSupport()).isEqualTo("enabled");
    }
}
