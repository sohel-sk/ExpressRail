package com.expressrail.services;

import com.expressrail.entities.Ticket;
import com.expressrail.entities.Train;
import com.expressrail.entities.User;
import com.expressrail.util.UserServiceUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class UserBookingService {
    private User user;
    private List<User> userList;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final String USERS_PATH = "app/src/main/java/com/expressrail/localDb/users.json";

    public UserBookingService(User user) throws IOException {
        this.user = user;
        loadUserListFromFile();
    }

    public UserBookingService() throws IOException{
       loadUserListFromFile();
    }

    private void loadUserListFromFile() throws IOException{

        userList = objectMapper.readValue(new File(USERS_PATH), new TypeReference<List<User>>() {});
    }

    public boolean loginUser(){
        Optional<User> foundUser = userList.stream().filter( user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
        }).findFirst();
        return foundUser.isPresent();
    }

    public boolean signUp(User tmpUser){
        try{
            userList.add(tmpUser);
            saveUserListToFile();
            return Boolean.TRUE;
        }catch (IOException e){
            return  Boolean.FALSE;
        }
    }


    private void saveUserListToFile() throws IOException{
        File usersFile = new File(USERS_PATH);
        objectMapper.writeValue(usersFile, userList);
    }
    public void fetchBooking(){
        user.printTickets();
    }

    public Boolean cancelBooking(String ticketID){
        List<Ticket> TicketsBooked = user.getTicketsBooked();
        Optional<Ticket> foundTicket = TicketsBooked.stream().filter(ticket -> {
            return ticket.getTicketId().equals(ticketID);
        }).findFirst();
        foundTicket.ifPresent(TicketsBooked::remove);
        user.setTicketsBooked(TicketsBooked);
        return Boolean.TRUE;
    }

    public List<Train> getTrains(String source, String destination){
        try{
            TrainService trainService = new TrainService();
            return trainService.searchTrains(source, destination);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<List<Integer>> fetchSeats(Train train){
        return train.getSeats();
    }

    public Boolean bookTrainSeat(Train train, int row, int seat ){
        try {
            List<List<Integer>> seats = fetchSeats(train);
            TrainService trainService = new TrainService();
            if(row >= 0 && row < seats.size() && seat >= 0 && seat <seats.get(row).size()){
                if(seats.get(row).get(seat) == 0){
                    seats.get(row).set(seat,1);
                    train.setSeats(seats);
                    trainService.addTrain(train);
                    return Boolean.TRUE;
                }else {
                    return Boolean.FALSE;
                }
            }else{
                return Boolean.FALSE;
            }
        } catch (Exception e) {
            return Boolean.FALSE;
        }

    }

}
