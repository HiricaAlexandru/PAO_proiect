/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pao_proiect;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author carja
 */
public class Auction {
    //private List<person> allAttendingPersons; //list with all the persons in the auction
    private final String NAME;
    private List<Bid> activeBids;
    private List<Bid> oldBids;
    private List<auctionObject> objects; // the list of objects for sale
    private LocalDateTime dateEnding;
    private LocalDateTime dateStarting;
    private List<Transaction> buyItNowList; //the list of sold objects
    
    Auction(LocalDateTime dateStarting, LocalDateTime dateEnding,String name){
        this.NAME = name;
        this.dateEnding = dateEnding;
        this.dateStarting = dateStarting;
        this.activeBids = new LinkedList();
        this.oldBids = new LinkedList();
        //this.allAttendingPersons = new LinkedList();
        this.objects = new LinkedList();
        this.buyItNowList = new LinkedList();
        
    }
    
    public boolean isAvailable(){
        LocalDateTime lt = LocalDateTime.now();
        return this.dateStarting.isBefore(lt) && lt.isBefore(this.dateEnding);
    }
    
    private void makeBidATransaction(Bid bid, Address addr) throws IllegalArgumentException{
        //makes a bid to become a transaction, with delivery address given as argument
        try{
            if(addr == null)
                throw new IllegalArgumentException("Can't have empty address");
            
            float price = getHighestPriceOfObject(bid.getObjectTraded());
            
            if(price == -1){
                throw new IllegalArgumentException("The bid is not an active one!");
            }
            
            
            this.removeActiveBidsOfObject(bid.getObjectTraded()); //remove all previous active bid of the object
            Transaction newTransaction = new Transaction(bid, addr, false); //make the new transaction object
            this.buyItNowList.add(newTransaction);//add the transaction to the sold list
            
            
            
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    public void makeBidATransaction(auctionObject obj, Address addr) throws IllegalArgumentException{
        //makes the highest bid a transaction
        try{
            Bid highestBid = this.getHighestBidOfObject(obj);
            makeBidATransaction(highestBid, addr);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    public void buyItNowOrder(Buyer buyer, int objectID, Address billingAddress) throws Exception{
        //method that makes a buy it now order
        try{
            
            auctionObject obj = getObjectWithId(objectID);
            
            float price = obj.getbuyItNowPrice();
            
            Bid newBid = new Bid(buyer, price, obj);
            newBid.validateData();
            
            
            
            Transaction newTrans = new Transaction(newBid, billingAddress, false);
            buyItNowList.add(newTrans);
            
            
            buyer.addExpiredBid(newBid);
            buyer.addTransaction(newTrans);
            
            obj.setSold(true);
            
            removeObjectAtAuction(obj);
            
        }catch(Exception e){
            throw new Exception("Couldn't make the order!");
        }
    }
    
    public void validateData() throws IllegalArgumentException{
        if(this.dateStarting.isAfter(dateEnding))
            throw new IllegalArgumentException("Start date after end date!");
        
        if(this.NAME == null)
            throw new IllegalArgumentException("The name is null!");
    }
    
    private void checkDuplicatesObject(auctionObject newObject) throws IllegalArgumentException{
        //method that checks if the object was already added
        
        for(auctionObject obj:objects){
            if(obj.getObjectId() == newObject.getObjectId()){
                throw new IllegalArgumentException("Duplicate Object!");
            }
        }
    }
    
    private void removeActiveBidsOfObject(auctionObject oldObject){
        //method that removes all active bids associated to an object
        
        Iterator<Bid> itr = this.activeBids.iterator();
        
        while(itr.hasNext()){
            Bid objTemp = itr.next();
            
            if(objTemp.getObjectTraded().getObjectId() == oldObject.getObjectId()){
                objTemp.getBuyer().addExpiredBid(objTemp);
                this.oldBids.add(objTemp); //put it in the old bids list
                itr.remove();
                break;
            }
        }
    }
    
    public void addObjectToAuction(auctionObject newObject) throws IllegalArgumentException{
        //add a new object to this auction
        //no duplicates allowed
        
        try{
            if(newObject.getSoldStatus() == true){
                throw new IllegalArgumentException("The object is not available!");
            }
            
            checkDuplicatesObject(newObject);
            this.objects.add(newObject);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
        
    }
    
    
    
    public void removeObjectAtAuction(auctionObject newObject){
        //delete an object at auction
        Iterator<auctionObject> itr = this.objects.iterator();
        
        while(itr.hasNext()){
            auctionObject objTemp = itr.next();
            
            if(objTemp.getObjectId() == newObject.getObjectId()){
                itr.remove();
                break;
            }
        }
    }
    
    private void checkIfObjectExist(auctionObject tryObj) throws IllegalArgumentException{
        //check if an object exists in this auction
        boolean ok = false;
        for(auctionObject obj:objects){
            if(obj.getObjectId() == tryObj.getObjectId())
                ok = true;
        }
        
        if(ok == false){
            throw new IllegalArgumentException("Object doesn't exist!");
        }
    }
    
    public auctionObject getObjectWithId(int objectId) throws NoClassDefFoundError{
        for(auctionObject obj:objects){
            if(obj.getObjectId() == objectId){
                return obj;
            }
        }
        throw new NoClassDefFoundError("No object found!");
    }
    
    
    private void removeOldBid(Bid newBid) throws IllegalArgumentException{
        //method that removes the old bid in place if it exists
        //throws exception if the new bid is lower than the old bid
        Iterator<Bid> itr = this.activeBids.iterator();
        
        while(itr.hasNext()){
            Bid objTemp = itr.next();
            
            if(newBid.getSeller().getUserId() == objTemp.getSeller().getUserId()
                    && newBid.getObjectTraded().getObjectId() == objTemp.getObjectTraded().getObjectId()){
                
                if(newBid.getPrice() <= objTemp.getPrice()){
                    throw new IllegalArgumentException("You cannot have a lower bid!");
                }
                

                objTemp.getBuyer().addExpiredBid(objTemp);//put the bid to the buyer's expired bid
                itr.remove(); //if it is the old bid them remove
                this.oldBids.add(objTemp); //put it in the old bids list
            }
        }
    }
    
    
    public void makeBidOnObject(Bid bid){
        try{
            checkIfObjectExist(bid.getObjectTraded());
            removeOldBid(bid);
            activeBids.add(bid);
            bid.getBuyer().addActiveBid(bid); //we add the bid of the object to the buyer
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Couldn't add bid!");
        }

        
    }
    
    public void makeBidOnObject(Buyer buyer, float price, auctionObject objectTraded){
        try{
            Bid bid_temporary = new Bid(buyer, price, objectTraded);
            bid_temporary.validateData();
            makeBidOnObject(bid_temporary);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Couldn't add bid!s");
        }
    }
    
    public Bid getHighestBidOfObject(auctionObject obj) throws IllegalArgumentException{
        //returns the highest bid of the object, returns the bid object
        for(Bid bid:this.activeBids){
            if(bid.getObjectTraded().getObjectId() == obj.getObjectId()){
                return bid;
            }
        }
        
        throw new IllegalArgumentException("The bid does not exist!");
    }
    
    public void finishAuction(){
        for(auctionObject obj : this.objects){
            Bid highestBid = this.getHighestBidOfObject(obj);
            this.makeBidATransaction(highestBid, highestBid.getBuyer().getAddress());//transform the bid to a transaction
        }
    }
    
    private float getHighestPriceOfObject(auctionObject obj){
        //returns the highest bid price of the object
        try{
            return getHighestBidOfObject(obj).getPrice();
        }catch(IllegalArgumentException e){
            //if the bid doesn't exist, then return -1
            return -1;
        }
    }
    
    public String getName(){
        return this.NAME;
    }
    
    @Override
    public String toString(){
        String name = "Auction name: " + this.NAME + '\n';
        String activeBids = "Available Objects\n";
        String oldObjects = "Sold Objects\n";
        
        if(this.objects.isEmpty()){
            activeBids = activeBids + "No objects!";
        }
        
        
        
      
        for(auctionObject obj:this.objects){
            activeBids = activeBids + obj.toString() + "\nHighest Bid: ";
            float price = getHighestPriceOfObject(obj);
            
            if(price == -1){
                activeBids = activeBids + "no bid made!\n";
            }else{
                activeBids = activeBids + String.valueOf(price) + '\n';
            }
        }
        
        
        if(this.buyItNowList.isEmpty()){
            oldObjects = oldObjects + "No objects!";
        }
       
        for(Transaction obj:this.buyItNowList){
            oldObjects = oldObjects + obj.toString() + "\n";
        }
        
        
        return name + activeBids + oldObjects;
    }
    
    
}
