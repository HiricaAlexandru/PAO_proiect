/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pao_proiect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author carja
 */
public class Seller extends user{
    private List<auctionObject> sellingList;
    private List<auctionObject> oldObjects; //objects that are no longer in sale (for tracking history reasons)

    public Seller(String firstName, String lastName, String phoneNumber, Address userAddress) {
        super(firstName, lastName, phoneNumber, userAddress);
        this.sellingList = new ArrayList<auctionObject>();
        this.oldObjects = new LinkedList<auctionObject>();
    }
    
    public Seller(){}
    
    public Seller(user usr){
        this(usr.getFirstName(), usr.getLastName(), usr.getPhoneNumber(), usr.getAddress());
    }
    
    public void addObjectForSale(auctionObject obj) throws IllegalStateException{
        //adds an object to the list of objects that a seller sells
        //if there are duplicates then it will throw an exception
        
        try{
           
            obj.setSeller(this);
        }catch(IllegalStateException e){
            throw new IllegalStateException();
        }
        
        for (auctionObject temp : sellingList) {
            if(temp.getObjectId() == obj.getObjectId())
                //if it already has this object for sale, then throw exception
                throw new IllegalStateException(); 
        }
        
        this.sellingList.add(obj);
        
    }
    
    public void removeObjectForSale(auctionObject obj){
        //removes an object from the list
        //if the objects doesn't belong to this owner then it will do nothing
        Iterator<auctionObject> itr = this.sellingList.iterator();
        
        while(itr.hasNext()){
            auctionObject objTemp = itr.next();
            
            if(objTemp.getObjectId() == obj.getObjectId()){
                itr.remove();
                obj.setSold(true);
                oldObjects.add(obj);//add to history
                break;
            }
        }
        
    }
    
    @Override
    public String toString(){

        String generalDescription = "Seller Information:";
        String generalInformation = super.toString();
        
        String ownedObjects = "The selller owns the following objects: ";
        
        String objectsString = new String();
        
        for(auctionObject obj:this.sellingList){
            if(obj.getSoldStatus() == false){
                objectsString = objectsString + obj.toString() + '\n';
            }
        }
       
        return generalDescription + '\n' + generalInformation + '\n' + objectsString;
    }
    
    
}
