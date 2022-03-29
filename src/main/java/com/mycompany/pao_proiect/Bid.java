/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pao_proiect;

/**
 *
 * @author carja
 */
public class Bid {
    private static long currentBidId = 0;
    protected long bidId;
    private Buyer buyer;
    private Seller seller;
    private float price;
    private auctionObject objectTraded;

    
    private void incrementCurrentBidId(){
        currentBidId += 1;
    }
    
    public Bid(Buyer buyer, float price, auctionObject objectTraded){
        incrementCurrentBidId();
        this.bidId = currentBidId;
        this.buyer = buyer;
        this.seller = objectTraded.getSeller();
        this.price = price;
        this.objectTraded = objectTraded;

    }


    public Bid() {
        incrementCurrentBidId();
        this.bidId = currentBidId;
        this.buyer = null;
        this.seller = null;
        this.price = 0;
        this.objectTraded = null;
    }
    
    public Buyer getBuyer() {
        return buyer;
    }
    
    public Seller getSeller() {
        return seller;
    }

    public float getPrice() {
        return price;
    }

    public auctionObject getObjectTraded() {
        return objectTraded;
    }

    @Override
    public String toString() {
        return "Buyer: "  + this.buyer.getFullName() + " ------- Seller: " 
                          + this.seller.getFullName() + " ------- price: " + this.price 
                          + " ------- ObjectId: " + this.objectTraded.getObjectId();
    }
    
    

    public void print(){
        System.out.println(this.toString());
    }
    
    public void validateData() throws IllegalArgumentException{
      if(price < 0 || this.price < this.objectTraded.getMinPrice()){
        throw new IllegalArgumentException("Error on bid creation") ;
      }
    }
    
}
