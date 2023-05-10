/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.theatre;

/**
 *
 * @author aibikuan
 */
public class Ticket {
    // attributes of the class
    private int row;
    private int seat;
    private double price;
    private Person person;
    
    // a constructor for Ticket
    public Ticket(int row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }
    
    // methods returning seat, row and price of Ticket.
    public int getSeat(){
       return seat;
    }
    
    public int getRow(){
       return row;
    }
    
    public double getPrice(){
       return price;
    }
    
    // method that prints out all info. 
    public void print() {
        System.out.println(person.getSurname() + ", " + person.getName() + " (" + person.getEmail() + ")");
        System.out.println("Row: " + row);
        System.out.println("Seat: " + seat);
        System.out.println("Price: £" + price);
    }
}
