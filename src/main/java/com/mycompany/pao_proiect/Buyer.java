/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pao_proiect;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author carja
 */
public class Buyer extends user{
    
    private List<Bid> activebuyerBids; //the list of transactions that a buyer has
    private List<Bid> oldBids;
    private List<Transaction> buyerTransactions;
    
    Buyer(String firstName, String lastName, String phoneNumber, Address userAddress){
        super(firstName,lastName,phoneNumber,userAddress);
        oldBids = new LinkedList<Bid>();
        activebuyerBids = new ArrayList<Bid>();
        buyerTransactions = new LinkedList<Transaction>();
    }
    
    Buyer(){}
    
    Buyer(user usr){
        this(usr.getFirstName(), usr.getLastName(), usr.getPhoneNumber(),usr.getAddress());
    }
    
    @Override
    public String toString(){

        String generalDescription = "Buyer Information:";
        String generalInformation = super.toString();
        
        String ownedObjects = "The buyer has the following bids:";
        
        String objectsString = new String();
        
        for(Bid obj:this.activebuyerBids){
 
            objectsString = objectsString + obj.toString() + '\n';
            
        }
        
        String transactions = "All transactions:";
        String transactionsString = new String();
        
        for(Transaction obj:this.buyerTransactions){
 
            transactionsString = transactionsString + obj.toString() + '\n';
            
        }
       
        return generalDescription + '\n' + generalInformation + '\n' + objectsString + '\n' + transactions + '\n' + transactionsString;
    }
    
    private void bidWasMade(Transaction bidToCheck, List<Transaction> listToCheck) throws IllegalArgumentException{
       for(Transaction bid:listToCheck){
           if(bidToCheck.bidId == bid.bidId)
               throw new IllegalArgumentException("The bid is already in the list!");
       }
   }
    
   private void bidWasMade(Bid bidToCheck, List<Bid> listToCheck) throws IllegalArgumentException{
       for(Bid bid:listToCheck){
           if(bidToCheck.bidId == bid.bidId)
               throw new IllegalArgumentException("The bid is already in the list!");
       }
   }
   
   private void deleteAllOccurenceOfObject(Bid newBid){
       //method that deletes all occurence of an object in the active bids
   
        Iterator<Bid> itr = this.activebuyerBids.iterator();
        
        while(itr.hasNext()){
            Bid objTemp = itr.next();
            
            if(objTemp.getObjectTraded().getObjectId() == newBid.getObjectTraded().getObjectId()){
                //if the temporary object is in the active bid trade
                itr.remove();
                this.oldBids.add(objTemp);//add the bid to the historic bids
            }
            
        }
   }
   
   public void addTransaction(Transaction newTransaction) throws IllegalArgumentException{
       try{
            
            bidWasMade(newTransaction, this.buyerTransactions);//first we check if a previous bid existed
            this.deleteAllOccurenceOfObject(newTransaction); //delete all pervious bids if they existed
            this.buyerTransactions.add(newTransaction);
            newTransaction.getObjectTraded().setSold(true);
         }catch(Exception e){
            throw new IllegalArgumentException(e.getCause());
        }
   }
   
    public void addExpiredBid(Bid oldBid) throws IllegalArgumentException{
        try{
            System.out.println(super.getFirstName());
            bidWasMade(oldBid,this.oldBids);
            deleteAllOccurenceOfObject(oldBid);
         }catch(Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
        
    }
    
    public void addActiveBid(Bid newBid) throws IllegalArgumentException{
        //first we need to check if the bid was already made
        try{
            bidWasMade(newBid,this.activebuyerBids); 
            deleteAllOccurenceOfObject(newBid); //delete all previous bids that were made of this particular object
            this.activebuyerBids.add(newBid); //if there are no duplicates then will append the new bid
        }catch(Exception e){
            throw new IllegalArgumentException(e.getCause());
        }
    }

    
    
}
