/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.theatre;

/**
 *
 * @author aibikuan
 */
public class Person {
    // attributes of the class
    private String name;
    private String surname;
    private String email;
    
    // a constructor for Person
    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
    
    // methods returning name, surname and email of Person
    public String getName(){
       return name;
    }
    
    public String getSurname(){
       return surname;
    }
    
    public String getEmail(){
       return email;
    }
}
