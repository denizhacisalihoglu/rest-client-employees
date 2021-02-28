package org.acme.rest.client;

public class Employee {

    public int id;
    public String name;
    public String surname;
    public String birthdate;
    public String occupation;


    public Employee(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname; 
    }
    
    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getSurname(){
        return this.surname;
    }

    public String getBirthdate(){
        return this.birthdate;
    }

    public String getOccupation(){
        return this.occupation;
    }
    
}
