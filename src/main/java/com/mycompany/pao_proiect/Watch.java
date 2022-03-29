/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pao_proiect;

import static java.lang.Math.abs;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 *
 * @author carja
 */
public class Watch extends auctionObject{
    
    //enum that holds watches materials
    public static enum MATERIAL {
        Gold,
        Platinum,
        Aluminium,
    }
    
    //enum that hold all the mechanism type of an watch
    public static enum MECHANISM{
        Automatic,
        Quartz,
        Mechanical,
    }
    
    private MATERIAL watchMaterial;
    private MECHANISM watchMechansim;
    private final static int VAT = 10; //the value added tax of this object type
    private final static float depositTaxPerDay = (float) 0.01;
    private String brandAndName;
    
    Watch(float minPrice, float buyItNowPrice, Seller objectOwner,LocalDateTime dateAdded, MATERIAL watchMaterial, MECHANISM watchMechansim,String brandAndName){
        super(minPrice, buyItNowPrice, objectOwner,dateAdded);
        this.watchMaterial = watchMaterial;
        this.watchMechansim = watchMechansim;
        this.brandAndName = brandAndName;
        
    }
    
    Watch(auctionObject obj, MATERIAL watchMaterial, MECHANISM watchMechansim,String brandAndName){
        this(obj.getMinPrice(),obj.buyItNowPrice,obj.getSeller(),obj.getDate(),watchMaterial,watchMechansim,brandAndName);
    }
    
    Watch(){
        super();
        this.watchMaterial = MATERIAL.Aluminium;
        this.watchMechansim = MECHANISM.Automatic;
    }
    
    @Override
    public void validateData() throws IllegalArgumentException{
        try{
            super.validateData();
            
            if(this.brandAndName == null){
                throw new IllegalArgumentException("Name is null!");
            }
            
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException();
        }
    }
    
    @Override
    public float getbuyItNowPrice() {
        LocalDateTime currentDate =  LocalDateTime.now();
        long noOfDaysBetween = abs(ChronoUnit.DAYS.between(this.getDate(), currentDate));
        return super.buyItNowPrice + (super.buyItNowPrice * ((float)VAT/100)) + noOfDaysBetween * depositTaxPerDay;
    }
    
}
