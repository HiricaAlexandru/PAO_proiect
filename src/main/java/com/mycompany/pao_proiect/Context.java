/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pao_proiect;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author carja
 */
public class Context {
    private List<Auction> auctionList;
    private List<user> allUsers;
    private List<auctionObject> allObjectsForSale;
    private List<Bid> bidsAndTransactions;
    
    private static Context ContextObject;
    
    private Context() { 
        this.allObjectsForSale = new LinkedList<auctionObject>();
        this.allUsers = new LinkedList<user>();
        this.auctionList = new LinkedList<Auction>();
        this.bidsAndTransactions = new LinkedList<Bid>();
        
    }  
    
    public void listAvailableAuctions(){
        for(Auction auction : this.getAuctionList()){
            if(auction.isAvailable())
                System.out.println(auction.getName());
        }
    }
    
    public void finishAllOldAuctions(){
        for(Auction auc : auctionList){
            if(auc.isAvailable() == false){
                auc.finishAuction();
            }
        }
        
    }    
    public auctionObject getObjectById(int id) throws IllegalArgumentException{
        for(auctionObject obj : this.allObjectsForSale){
            if(obj.getObjectId() == id){
                return obj;
            }
        }
        
        throw new IllegalArgumentException("No object found!");
    }
    
    public Buyer getBuyerById(int id) throws IllegalArgumentException{
        for(user usr : this.allUsers){
            if(usr instanceof Buyer && usr.getUserId() == id ){
                return (Buyer) usr;
            }
        }
        
        throw new IllegalArgumentException("No buyer found!");
    }
    
     public Auction getAuctionByName(String name) throws Exception{
        for(Auction auc : this.auctionList){
            if(auc.getName().equals(name)){
                return auc;
            }
        }
        
        throw new Exception("Auction not found!");
    }
    
    
    
    public Seller getSellerById(int id) throws IllegalArgumentException{
        for(user usr: allUsers){
            if(usr.getUserId() == id){
                if(usr instanceof Seller){
                    return (Seller) usr;
                }else{
                    throw new IllegalArgumentException("The user is not a seller!");
                }
            }
        }
        
        throw new IllegalArgumentException("The user was not found!");
            
    }
    
    public void addAuction(Auction newAuction){
        auctionList.add(newAuction);
    }
    
    public void addUser(user newUser){
        allUsers.add(newUser);
    }
    
    public void addObjectForSale(auctionObject newObject){
        allObjectsForSale.add(newObject);
    }
    
    public void addBid(Bid newBid){
        bidsAndTransactions.add(newBid);
    }
    
    public static Context getInstance() {    
        if (ContextObject==null)  
        {  
               ContextObject = new Context();  
        }  
        return ContextObject;  
    }  

    public List<Auction> getAuctionList() {
        return auctionList;
    }

    public void setAuctionList(List<Auction> auctionList) {
        this.auctionList = auctionList;
    }

    public List<user> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(List<user> allUsers) {
        this.allUsers = allUsers;
    }

    public List<auctionObject> getAllObjectsForSale() {
        return allObjectsForSale;
    }

    public void setAllObjectsForSale(List<auctionObject> allObjectsForSale) {
        this.allObjectsForSale = allObjectsForSale;
    }

    public List<Bid> getBidsAndTransactions() {
        return bidsAndTransactions;
    }

    public void setBidsAndTransactions(List<Bid> bidsAndTransactions) {
        this.bidsAndTransactions = bidsAndTransactions;
    }

    public static Context getContextObject() {
        return ContextObject;
    }

    public static void setContextObject(Context ContextObject) {
        Context.ContextObject = ContextObject;
    }
}
