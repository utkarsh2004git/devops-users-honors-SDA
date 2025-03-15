package com.rcoem.devops.services;

import com.rcoem.devops.dto.UserInfo;
import com.rcoem.devops.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserInfoRepository userInfoRepository;


    public List<UserInfo> getAllUsers(){
        return userInfoRepository.getAllUsers();
    }

    public UserInfo createUser(UserInfo userInfo){
        return userInfoRepository.createUser(userInfo);

    }

    public UserInfo getUser(String id) throws Exception{
        UserInfo userInfo= userInfoRepository.getUserById(id);;
        if(userInfo == null){
            throw new Exception("User not found");
        }
        return userInfo;
    }

    public void updateUserOrder(String userId, String orderId) {

        userInfoRepository.updateUserOrder(userId,orderId);
    }
}
