package com.expressrail.entities;

import java.util.List;

public class User {

    private String name;
    private String password;
    private String hashPassword;
    private List<Ticket> ticketsBooked;
    private String userId;
}
