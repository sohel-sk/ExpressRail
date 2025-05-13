package com.expressrail.services;

import com.expressrail.entities.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class UserBookingService {
    private User user;
    private List<User> userList;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final String USERS_PATH = "../localDB/users.json";

    public UserBookingService(User user) throws IOException {
        this.user = user;
        File users = new File(USERS_PATH);
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {});

    }

    public boolean loginUser(){
        return true;
    }

    public boolean signUp(User tmpUser){
        return true;
    }
}
