/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pao_proiect;

/**
 *
 * @author carja
 */
public abstract class user implements person{ //abstract trebuie
    protected static int currentId = 0; //variable that is incremented when another user is added   
    
    private int userId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Address userAddress;

    public user(String firstName, String lastName, String phoneNumber, Address userAddress) {
        currentId += 1;//incrementing the value, because the id's of persons must be unique
        this.userId = currentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.userAddress = userAddress;
    }

    public user() {
        currentId += 1;//incrementing the value, because the id's of persons must be unique
        this.userId = currentId;
        this.firstName = null;
        this.lastName = null;
        this.phoneNumber = null;
        this.userAddress = null;
    }
    
    public void validData() throws IllegalArgumentException{
        if(this.firstName == null || this.lastName == null || this.phoneNumber == null){
            throw new IllegalArgumentException("Invalid user data!");
        }
    }


    @Override
    public void setFirstName(String firstName) {
        try{
            
            if(this.firstName != null){
                throw new IllegalArgumentException() ;
            }
            this.firstName = firstName;
        }catch(IllegalArgumentException e){
            System.out.println("Numele a fost deja initializat!");
        }
        
    }

    @Override
    public void setLastName(String lastName) {
        try{
            
            if(this.lastName != null){
                throw new IllegalArgumentException() ;
            }
            this.lastName = lastName;
        }catch(IllegalArgumentException e){
            System.out.println("Numele a fost deja initializat!");
        }
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        try{
            
            if(phoneNumber.length() > 16){
                throw new IllegalArgumentException() ;
            }
            this.phoneNumber = phoneNumber;
        }catch(IllegalArgumentException e){
            System.out.println("Numarul nu poate avea o lungime mai mare de 16");
        }
        
    }

    @Override
    public void setAddressContact(Address userAddress) {
        this.userAddress = userAddress;
    }
    
    

    protected static int getCurrentId() {
        return currentId;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String getAddressContact() {
        return userAddress.toString();
    }

    @Override
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
    
    public Address getAddress(){
        return this.userAddress;
    }

    @Override
    public void setFullName(String firstName, String lastName) {
        try{
            
            if(this.lastName != null || this.firstName != null){
                throw new IllegalArgumentException() ;
            }
            this.lastName = lastName;
            this.firstName = firstName;
        }catch(IllegalArgumentException e){
            System.out.println("Numele a fost deja initializat!");
        }
    }
    
    @Override
    public String toString(){
        String fullName = "Complete name: " + this.getFullName();
        String phoneNumber = "Phone number: " + this.phoneNumber;
        String address = this.userAddress.toString();
        return fullName + '\n' + phoneNumber + '\n' + address;
    }
    
  
    
}
