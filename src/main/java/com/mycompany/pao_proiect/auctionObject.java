/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pao_proiect;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 *
 * @author carja
 */
public abstract class auctionObject {
    protected static int currentId = 0; //variable that is incremented when another object is added  
    
    private int objectId;   //the Id of the object
    private float minPrice; //the minimum bid price for selling
    protected float buyItNowPrice; //the buy it out price 
    private Seller objectOwner;  //reference to the owner of the object
    private LocalDateTime dateAdded;
    private boolean sold; //if the item was sold or it is already available
    
    static private void incrementCurrentId(){
        currentId += 1;
    }
    
    auctionObject(){
        incrementCurrentId();
        dateAdded = LocalDateTime.now();
        this.objectId = currentId;   
        minPrice = 0;
        buyItNowPrice = 0; 
        objectOwner = null;  
        sold = false;
    }
    
  
    
    auctionObject(float minPrice, float buyItNowPrice, Seller objectOwner, LocalDateTime dateAdded){
            incrementCurrentId();
            this.dateAdded = dateAdded;
            this.objectId = currentId; 
            this.minPrice = minPrice;
            this.buyItNowPrice = buyItNowPrice;
            this.objectOwner = objectOwner;
            objectOwner.addObjectForSale(this);

    }
    
    public void validateData() throws IllegalArgumentException{
      LocalDateTime currentDate;
      currentDate = LocalDateTime.now();
      
      if(buyItNowPrice < 0 || minPrice < 0 || currentDate.isBefore(dateAdded) || objectOwner == null){
              throw new IllegalArgumentException("The object is not right!") ;
      }
    }
    
    public int getObjectId() {
        return objectId;
    }
    
    public LocalDateTime getDate(){
        return this.dateAdded;
    }
    
    public boolean getSoldStatus(){
        return this.sold;
    }
    
    public Seller getSeller(){
        return this.objectOwner;
    }
    
    public void setSeller(Seller objectOwner) throws IllegalStateException {
     
        //if the object already has an owner that is not the current owner throw exception
        if(this.objectOwner != null && objectOwner != this.objectOwner){
            throw new IllegalStateException();
        }
        
        if(this.objectOwner == null){
            this.objectOwner = objectOwner;
            //objectOwner.addObjectForSale(this);
        }
    }
    
    public void setSold(boolean soldStatus){
        this.sold = soldStatus;
    }
    
    @Override 
    public String toString(){
        String objID = "ID: " + String.valueOf(objectId);
        String minPriceString = "Minimum price: " + String.valueOf(minPrice);
        String buyString = "Buy it now price: " + String.valueOf(this.getbuyItNowPrice());
        String sellerInfo = "Seller name: " + objectOwner.getFullName();
        String dateBroughtIn = "Date deposited: " + this.dateAdded.format(DateTimeFormatter.ISO_DATE);
        String soldStatus = "Sold: " + String.valueOf(this.sold);
        
        return objID + '\n' + minPriceString + '\n' +  buyString + '\n' 
                + sellerInfo + '\n' + dateBroughtIn + '\n' + soldStatus;

    }
    
    
    public float getMinPrice() {
        return minPrice;
    }
    
    //method that adds the VAT + number o stored days at the deposit to the buy
    //now price
    public abstract float getbuyItNowPrice();

        
    
}
