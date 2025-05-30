/*
 * This source file was generated by the Gradle 'init' task
 */
package com.expressrail;

import com.expressrail.entities.Train;
import com.expressrail.entities.User;
import com.expressrail.services.UserBookingService;
import com.expressrail.util.UserServiceUtil;

import java.io.IOException;
import java.util.*;

public class App {
    public static void main(String[] args) {
        System.out.println("Running Train Booking System.");
        Scanner scanner = new Scanner(System.in);

        int option = 0;
        UserBookingService userBookingService ;
        try {
            userBookingService = new UserBookingService();
        }catch (IOException ex){
            System.out.println("IOexception occured: "+ex.getMessage());
            return;
        }

        while(option != 7){
            System.out.println("Choose option :-");
            System.out.println("1. Sign Up");
            System.out.println("2. Log In");
            System.out.println("3. Fetch Bookings");
            System.out.println("4. Search Trains");
            System.out.println("5. Book a Seat");
            System.out.println("6. Cancel the Booking");
            System.out.println("7. Exit the App");
            option = scanner.nextInt();
            Train trainSelectedForBooking = new Train();
            switch (option){
                case 1:{
                    System.out.println("Enter the username to sign up");
                    String nameToSignUp = scanner.next();
                    System.out.println("Enter the Password to Sign Up");
                    String passwordToSignUp =scanner.next();
                    User userToSignUp = new User(nameToSignUp, passwordToSignUp, UserServiceUtil.hashPasssword(passwordToSignUp), new ArrayList<>(), UUID.randomUUID().toString());
                    userBookingService.signUp(userToSignUp);
                    break;
                }
                case 2:{
                    System.out.println("Enter the username to Login : ");
                    String nameTOLogIn = scanner.next();
                    System.out.println("Enter the Password");
                    String passwordToLogIn = scanner.next();
                    User userToLogIn = new User(nameTOLogIn, passwordToLogIn,
                            UserServiceUtil.hashPasssword(passwordToLogIn),
                            new ArrayList<>(),UUID.randomUUID().toString() );
                    try{
                        userBookingService= new UserBookingService(userToLogIn);
                    } catch (IOException e) {
                        return;
                    }
                    break;
                }
                case 3:{
                    System.out.println("fetching your Bookings");
                    userBookingService.fetchBooking();
                    break;
                }
                case 4:{
                    System.out.println("Enter the Source Station :");
                    String source = scanner.next();
                    System.out.println("Enter the destination station :");
                    String destination = scanner.next();
                    List<Train> trains = userBookingService.getTrains(source, destination);
                    int index = 1;
                    for (Train t : trains){
                        System.out.println(index+" Train id : "+t.getTrainId());
                        for (Map.Entry<String, String> entry : t.getStationTimes().entrySet()){
                            System.out.println("station "+entry.getKey()+" time : "+entry.getValue());
                        }
                    }
                    System.out.println("Enter the index of the train to select the train : " );
                    trainSelectedForBooking = trains.get(scanner.nextInt());

                    break;
                }
                case 5:{
                    System.out.println("Select a seat uot of these seats :");
                    List<List<Integer>> seats = userBookingService.fetchSeats(trainSelectedForBooking);
                    for(List<Integer> row:seats){
                        for(Integer val : row){
                            System.out.print(val+" ");
                        }
                        System.out.println();
                    }
                    System.out.println("Enter the row and Column for the selected seat :");
                    System.out.println("Row : ");
                    int row = scanner.nextInt();
                    System.out.println("Column : ");
                    int col = scanner.nextInt();
                    System.out.println("Booking your seat ....");
                    Boolean booked = userBookingService.bookTrainSeat(trainSelectedForBooking, row, col);
                    if(booked.equals(Boolean.TRUE)) System.out.println("Seat Booked! Enjoy your Journey");
                    else System.out.println("can't boook this seat");
                    break;
                }
                case 6:{
                    userBookingService.fetchBooking();
                    System.out.println("Enter the Ticket Id to Cancel the Ticket");
                    String ticketID = scanner.next();
                    Boolean removed =userBookingService.cancelBooking(ticketID);
                    if(removed.equals(Boolean.TRUE)) System.out.println("Your Ticket with booking ID "+ticketID+" has been cancelled, the refund will be processed within 48 working hours");
                    else System.out.println("The Ticket with Booking Id "+ticketID+" had been used!");
                    break;
                }
                default: {
                    break;
                }
            }
        }
    }
}
