package com.rcoem.devops.repository;

import com.rcoem.devops.dto.UserInfo;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class UserInfoRepository {

    Map<String, UserInfo> userInfoTable;


    @PostConstruct
    public void init() {
        userInfoTable = new HashMap<>();
    }


    public List<UserInfo> getAllUsers(){
        return userInfoTable.values()
                .stream().collect(Collectors.toList());
    }

    public UserInfo createUser(UserInfo userInfo) {
        String userId = UUID.randomUUID().toString();
        UserInfo updatedUser = userInfo.toBuilder().id(userId).build();
        this.userInfoTable.put(userId, updatedUser);
        return updatedUser;
    }


    public UserInfo getUserById(String userId){
        return userInfoTable.get(userId);
    }

    public void updateUserOrder(String userId, String orderId) {
        UserInfo userInfo = userInfoTable.get(userId);
        if (userInfo != null) {
            // Ensure orders list is initialized
            if (userInfo.getOrders() == null) {
                userInfo.setOrders(new ArrayList<>());
            }

            // Add orderId if it's not already in the list
            if (!userInfo.getOrders().contains(orderId)) {
                userInfo.getOrders().add(orderId);
            }

            // Put the updated user info back into the map
            userInfoTable.put(userId, userInfo);
        }
    }







}
