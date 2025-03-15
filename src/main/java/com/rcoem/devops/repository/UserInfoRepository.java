package com.rcoem.devops.repository;

import com.rcoem.devops.dto.UserInfo;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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

}
