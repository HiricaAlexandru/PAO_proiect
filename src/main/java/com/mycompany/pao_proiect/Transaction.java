/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pao_proiect;

/**
 *
 * @author carja
 */
public class Transaction extends Bid {
    private Address billingAddress;
    private boolean shipped;

    public Transaction(Buyer buyer, float price, auctionObject objectTraded,Address billingAddress, boolean shipped) {
        super(buyer, price, objectTraded);
        super.getObjectTraded().getSeller().removeObjectForSale( super.getObjectTraded()); //if a transaction was made, then remove the object from the seller's list
        super.getBuyer().addTransaction(this);
        this.billingAddress = billingAddress;
        this.shipped = shipped;

    }
    
    public Transaction(Bid bid, Address billingAddress, boolean shipped){
        super(bid.getBuyer(), bid.getPrice(), bid.getObjectTraded());
        super.getObjectTraded().getSeller().removeObjectForSale( super.getObjectTraded()); //if a transaction was made, then remove the object from the seller's list
        super.getBuyer().addTransaction(this);
        this.billingAddress = billingAddress;
        this.shipped = shipped;
        this.getObjectTraded().setSold(true);
    }
    
    public Transaction(Address billingAddress) {
        this.shipped = false;
        this.billingAddress = billingAddress;
        this.getObjectTraded().setSold(true);
    }
    
    public boolean getShippedStatus(){
        return this.shipped;
    }
    
    public void changeToShipped(){
        this.shipped = true;
    }
    
    @Override
    public String toString(){
        String fatherString = super.toString();
        String transactionString = '\n' + this.billingAddress.toString() + " " + "shipped status: " + shipped;
        return fatherString + transactionString;
    }
    
    @Override
    public void print(){
        System.out.println(this.toString());
    }
}
