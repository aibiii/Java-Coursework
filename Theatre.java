/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.theatre;

/**
 *
 * @author aibikuan
 */

import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Theatre {

    private static final int[] row1 = new int[12];
    private static final int[] row2 = new int[16];
    private static final int[] row3 = new int[20];
    
    private static final File fileName = new File("data.txt");
    
    private static ArrayList<Ticket> tickets = new ArrayList<Ticket>();

    public static void main(String[] args) {
        System.out.println("Welcome to the New Theatre!");
        
        while (true) {
            System.out.println("-------------------------------------------------\n"
                + "Please select an option:\n" + "     1): Buy a ticket\n"
                + "     2): Print seating area\n" + "     3): Cancel ticket\n"
                + "     4): List available seats\n" + "     5): Save to file\n"
                + "     6): Load from file\n" + "     7): Print ticket information and total price\n"
                + "     8): Sort tickets by price\n" + "     0): Quit\n" 
                + "-------------------------------------------------");

            Scanner input = new Scanner(System.in);
            System.out.print("Enter option: ");
            
            // this line makes sure that input is an integer.
            try {
                int option = input.nextInt();
                while(option != 0) {
                    switch (option) {
                        case 1:
                            buy_ticket();
                            break;
                        case 2:
                            print_seating_area();
                            break; 
                        case 3:
                            cancel_ticket();
                            break;
                        case 4:
                            show_available();
                            break;
                        case 5:
                            save();
                            break;
                        case 6:
                            load();
                            break;
                        case 7:
                            show_ticket_info();
                            break;
                        case 8:
                            sort_tickets();
                            break;
                        default:
                            System.out.println("Wrong option.");
                            break;
                    }
                    break;
                }
                if(option == 0) break;
            } catch (InputMismatchException e) {
                System.out.println("Please, input a number.");
            }
        }       
    }
    
    // base of the code used from lecture slides Week 8
    // the recursive method mergeSort() separates the ArrayList for simpler sorting.
    public static void mergeSort(ArrayList<Ticket> tickets, int lowIndex, int highIndex) {
        int mid = (lowIndex + highIndex)/2;
        if(lowIndex < highIndex) {
            mergeSort(tickets, lowIndex, mid);
            mergeSort(tickets, mid+1, highIndex);
            merge(tickets, lowIndex, mid, highIndex);
        }
    }
    
    // base of the code used from lecture slides Week 8
    // this function sorts and merges sorted lists together.
    public static void merge(ArrayList<Ticket> tickets, int lowIndex, int mid, int highIndex) {
        int left = mid - lowIndex + 1;
        int right = highIndex - mid;
        Ticket[] arrayLeft = new Ticket[left];
        Ticket[] arrayRight = new Ticket[right];
        int k = 0; 
        
        for (int i = 0; i < left; i++) {
            arrayLeft[i] = tickets.get(k);
            k++;
        }
        for (int j = 0; j < right; j++) {
            arrayRight[j] = tickets.get(k);
            k++;
        }
        
        int index1 = 0, index2 = 0;
        k = lowIndex;
        
        while(index1 < left && index2 < right) {
            if (arrayLeft[index1].getPrice() <= arrayRight[index2].getPrice()) {
                tickets.set(k, arrayLeft[index1]);
                index1++;
            }
            else {
                tickets.set(k, arrayRight[index2]);
                index2++;
            }
            k++;
        }
        while(index1 < left) {
            tickets.set(k, arrayLeft[index1]);
            index1++;
            k++;
        }
        while(index2 < right) {
            tickets.set(k, arrayRight[index2]);
            index2++;
            k++;
        }
    }
    
    // sorts the tickets by price(ascending).
    private static void sort_tickets() {
        // crreating new ArrayList, so that the old one will not change.
        ArrayList<Ticket> sortedByPrices = new ArrayList<Ticket>(tickets.size());
        for (int i = 0; i < tickets.size(); i++) {
            sortedByPrices.add(i, tickets.get(i));
        }
        mergeSort(sortedByPrices, 0, sortedByPrices.size()-1);
        
        for (int i = 0; i < sortedByPrices.size(); i++) {
            System.out.println("** Ticket details: ");
            System.out.print("Surname, name (email): ");
            sortedByPrices.get(i).print();
        }
    }
    
    // prints the list sorted by the order that user bought the tickets.
    private static void show_ticket_info() {
        double totalPrice = 0;
        for (int i = 0; i < tickets.size(); i++) {
            System.out.println("** Ticket details: ");
            System.out.print("Surname, name (email): ");
            tickets.get(i).print();
            totalPrice += tickets.get(i).getPrice();
        }
        System.out.println("Total price: £" + totalPrice);
    }
    
    // loading saved seats from "data.txt" file.
    private static void load() {
        try {
            Scanner reader = new Scanner(fileName);
            
            int index = 0;
            while (reader.hasNextInt()) {
                int seat = reader.nextInt();
                if (index < row1.length) row1[index] = seat;
                else if (index < row2.length) row2[index] = seat;
                else if (index < row3.length) row3[index] = seat;
                index++;
            }
            reader.close();
            System.out.println("Loaded data from " + fileName);
        } 
        catch (IOException e) {
            System.out.println("An error occurred, please try again.");
        }
        
    }
    
    // saving all seats into "data.txt" file.
    private static void save() {
        try {
            FileWriter writer = new FileWriter(fileName);
            for (int i = 0; i < row1.length; i++) {
                writer.write(row1[i] + " ");
            }
            writer.write("\n");
            for (int i = 0; i < row2.length; i++) {
                writer.write(row2[i] + " ");
            }
            writer.write("\n");
            for (int i = 0; i < row3.length; i++) {
                writer.write(row3[i] + " ");
            }
            writer.write("\n");
            writer.close();
            System.out.println("Data saved to " + fileName);
        } 
        catch (IOException e) {
            System.out.println("An error occurred, please try again.");
        }
    }
    
    private static void show_available() {
        System.out.print("Seats available in row 1: ");
        for(int i = 0; i < row1.length; i++) {
            if (row1[i] == 0) {
                if (i < row1.length-1) System.out.print(i+1 + ", ");
                // this line ensures that when index i is the last index of an array, it prints last index and dot instead of a comma.
                else System.out.print(i+1 + ".");
            }
        }
        System.out.println();
        
        System.out.print("Seats available in row 2: ");
        for(int i = 0; i < row2.length; i++) {
            if (row2[i] == 0) {
                if (i < row2.length-1) System.out.print(i+1 + ", ");
                else System.out.print(i+1 + ".");
            }
        }
        System.out.println();
        System.out.print("Seats available in row 3: ");
        for(int i = 0; i < row3.length; i++) {
            if (row3[i] == 0) {
                if (i < row3.length-1) System.out.print(i+1 + ", ");
                else System.out.print(i+1 + ".");
            }
        }
        System.out.println();
    }
    
    private static void cancel_ticket() {
        System.out.println("Cancelling the ticket");
        Scanner input = new Scanner(System.in);
        
        // the while looped will be continuing to loop until the user inserts existing row number.
        while (true) {
            // makes sure that input is an integer.
            try {
                System.out.println("Please choose a row number: ");
                int row = input.nextInt();

                while(row <= 0 || row > 3) {
                    System.out.println("Row not valid. Please enter 1-3.");
                    break;
                }
                if (row > 0 && row <= 3) {
                    System.out.println("Please choose a seat number: ");
                    int seat = input.nextInt();

                    if (row == 1) {
                        // this loop goes over every ticket in tickets ArrayList, and cancells the one user has input.
                        for (int i = 0; i < tickets.size(); i++) {
                            Ticket ticket = tickets.get(i);

                            // if user inputs non-existing seat, the loop breaks and goes back to menu.
                            if (seat > 0 && seat <= row1.length) {
                                if(row1[seat-1] == 0) System.out.println("Error. This seat has not been sold");
                                else if (row1[seat-1] == 1) {
                                    row1[seat-1]--;
                                    // makes sure that the correct seat is removed from ArrayList
                                    if (seat == ticket.getSeat()) tickets.remove(i);
                                    System.out.println("Cancelled seat is: row " + row + ", seat number " + seat);
                                }
                            }    
                            else {
                                System.out.println("Please choose existing seat.");
                                break;
                            }
                            break;
                        }
                        // breaks every time the for loop finishes. whether user inputs worng or right values, brings back to menu.
                        break;
                    }
                    else if(row == 2) {
                        for (int j = 0; j < tickets.size(); j++) {
                            Ticket ticket = tickets.get(j);

                            if (seat > 0 && seat <= row2.length) {
                                if(row2[seat-1] == 0) System.out.println("Error. This seat has not been sold");
                                else if(row2[seat-1] == 1) {
                                    row2[seat-1]--;
                                    if (seat == ticket.getSeat()) tickets.remove(j);
                                    System.out.println("Cancelled seat is: row " + row + ", seat number " + seat);
                                }
                            }    
                            else {
                                System.out.println("Please choose existing seat.");
                                break;
                            }
                            break;
                        }
                        break;
                    }
                    else if (row == 3) {
                        for (int k = 0; k < tickets.size(); k++) {
                            Ticket ticket = tickets.get(k);

                            if (seat > 0 && seat <= row3.length) {
                                if(row3[seat-1] == 0) System.out.println("Error. This seat has not been sold");
                                else if(row3[seat-1] == 1) {
                                    row3[seat-1]--;
                                    if (seat == ticket.getSeat()) tickets.remove(k);
                                    System.out.println("Cancelled seat is: row " + row + ", seat number " + seat);
                                }
                            }    
                            else {
                                System.out.println("Please choose existing seat.");
                                break;
                            }
                            break;
                        }
                        break;
                    } 
                }
            } catch (InputMismatchException e) {
                System.out.println("Please, input a number.");
                break;
            }
        } 
    }

    private static void print_seating_area() {
        System.out.println("     ***********\n" 
                + "     *  STAGE  *\n" 
                + "     ***********\n");
        System.out.print("     ");
        for(int i = 0; i < row1.length; i++) {
            // when the seat is available(equals to 0), it prints O
            if(row1[i] == 0) {
                // when index i equals half, it makes a space between the seats.
                if (i == row1.length/2) System.out.print(" ");
                System.out.print("O");
            }
            // when the seat is occupied(equals to 1), it prints X
            else if(row1[i] == 1) {
                if (i == row1.length/2) System.out.print(" ");
                System.out.print("X");
            } 
        }
        System.out.println("\n");
        System.out.print("   ");
        for(int i = 0; i < row2.length; i++) {
            if(row2[i] == 0) {
                if(i == row2.length/2) System.out.print(" ");
                System.out.print("O");
            }
            else if(row2[i] == 1) {
                if(i == row2.length/2) System.out.print(" ");
                System.out.print("X");
            }
        }
        System.out.println("\n");
        System.out.print(" ");
        for(int i = 0; i < row3.length; i++) {
            if(row3[i] == 0) {
                if(i == row3.length/2) System.out.print(" ");
                System.out.print("O");
            }
            else if(row3[i] == 1) {
                if(i == row3.length/2) System.out.print(" ");
                System.out.print("X");
            }
        }
        System.out.println("\n");

    }
    
    private static void buy_ticket() {
        Scanner input = new Scanner(System.in);
        
        // the while looped will be continuing to loop until the user inserts existing row number.
        while (true) {
            // makes sure that input is an integer.
            try {
                System.out.println("Please choose a row number: ");
                int row = input.nextInt();


                while(row <= 0 || row > 3) {
                    System.out.println("This row is not valid. Please enter 1-3.");
                    break;
                }
                if (row > 0 && row <= 3) {
                    System.out.println("Please choose a seat number: ");
                    int seat = input.nextInt();
                    if (row == 1) {
                        // if user inputs non-existing seat, the loop breaks and goes back to menu.
                        if (seat > 0 && seat <= row1.length) {
                            if(row1[seat-1] == 1) {
                                System.out.println("Please choose another seat, this seat is not available");
                                break;
                            }
                            else if(row1[seat-1] == 0) {
                                row1[seat-1]++;
                                System.out.println("Your seat is: row " + row + ", seat number " + seat);
                            }
                        }
                        else {
                            System.out.println("Please choose existing seat.");
                            break;
                        }
                    }
                    else if(row == 2) {
                        if (seat > 0 && seat <= row2.length) {
                            if(row2[seat-1] == 1) {
                                System.out.println("Please choose another seat, this seat is not available");
                                break;
                            }
                            else if(row2[seat-1] == 0) {
                                row2[seat-1]++;
                                System.out.println("Your seat is: row " + row + ", seat number " + seat);
                            }
                        }
                        else {
                            System.out.println("Please choose existing seat.");
                            break;
                        }
                    }
                    else if (row == 3) {
                        if (seat > 0 && seat <= row3.length) {
                            if(row3[seat-1] == 1) {
                                System.out.println("Please choose another seat, this seat is not available");
                                break;
                            }
                            else if(row3[seat-1] == 0) {
                                row3[seat-1]++;
                                System.out.println("Your seat is: row " + row + ", seat number " + seat);
                            }
                        }    
                        else {
                            System.out.println("Please choose existing seat.");
                            break;
                        }
                    }

                    // asking for persons details only after choosing the exisitng seat.
                    System.out.println("Name: ");
                    String name = input.next();
                    System.out.println("Surname: ");
                    String surname = input.next();
                    System.out.println("Email: ");
                    String email = input.next();
                    System.out.println("Price: ");
                    double price = input.nextDouble();

                    // creates a ticket with all details and adds into tickets arraylist.
                    Person person = new Person(name, surname, email);
                    Ticket ticket = new Ticket(row, seat, price, person);
                    tickets.add(ticket);

                    break;
                } 
            } catch (InputMismatchException e) {
                System.out.println("Please, input a number.");
                break;
            }
        }
    }
}
