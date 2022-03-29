/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pao_proiect;

/**
 *
 * @author carja
 */

interface person {
    //getters for persons
    public String getFirstName(); 
    public String getLastName();
    public String getFullName();
    public String getPhoneNumber();
    public String getAddressContact();
    
    //setters for persons
    public void setFirstName(String firstName); 
    public void setLastName(String lastName);
    public void setFullName(String firstName, String lastName);
    public void setPhoneNumber(String phoneNumber);
    public void setAddressContact(Address address);
}
