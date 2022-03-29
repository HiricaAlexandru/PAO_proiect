/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pao_proiect;

/**
 *
 * @author carja
 */
public class Address {
    private String streetName;
    private String countryName;
    private String cityName;
    private String flatNameAndNumber;

    public Address(String streetName, String countryName, String cityName, String flatNameAndNumber) {
        this.streetName = streetName;
        this.countryName = countryName;
        this.cityName = cityName;
        this.flatNameAndNumber = flatNameAndNumber;
    }

    public Address() {
        this.streetName = null;
        this.countryName = null;
        this.cityName = null;
        this.flatNameAndNumber = null;
    }
    
    public void validateData() throws IllegalArgumentException{
        if(this.cityName == null || this.countryName == null || this.flatNameAndNumber == null || this.streetName == null)
            throw new IllegalArgumentException("One of the fields is null!");
        
        
    }
    
    @Override
    public String toString() {
        return "The address is:" +
        " country: " + countryName + 
        ", city name: " + cityName + 
        ", street name: " + streetName + 
        ", name of house and number: " + flatNameAndNumber + '.';
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setFlatNameAndNumber(String flatNameAndNumber) {
        this.flatNameAndNumber = flatNameAndNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getFlatNameAndNumber() {
        return flatNameAndNumber;
    }
    
    
}
