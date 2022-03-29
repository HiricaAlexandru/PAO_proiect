/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pao_proiect;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carja
 */
public class ConsoleInterface implements UserInterface{

    private static ConsoleInterface consoleInterface;
    
    private static Scanner sc;
    
   
    private ConsoleInterface() {  
        sc = new Scanner(System.in);
    }  
    
    public static ConsoleInterface getInstance() {
        //method that makes this class a singleton
        if (consoleInterface==null)  
        {  
               consoleInterface = new ConsoleInterface();  
        }  
        return consoleInterface;  
    }  
    
    private Address readAddress() throws IllegalArgumentException{
        //method that reads the address from the console
        System.out.print("Introduce the name of the country: ");
        String countryName = sc.nextLine();
        System.out.print("Introduce city name: ");
        String cityName = sc.nextLine();
        System.out.print("Introduce street name: ");
        String streetName = sc.nextLine();
        System.out.print("Introduce flat info: ");
        String flatNameAndNumber = sc.nextLine();
        
        try{
            Address addr = new Address(streetName, countryName, cityName, flatNameAndNumber);
            addr.validateData();
            return addr;
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }

    }
    
    private user readUser(user newUser){
        //read method for introduction of a new user
        //the method has as argument an user(abstract class, so a Buyer or a Seller must be sent)
        System.out.print("Introduce the first name: ");
        sc.nextLine();
        String firstName = sc.nextLine();
        System.out.print("Introduce the last name: ");
        String lastName = sc.nextLine();
        System.out.print("Introduce the phone number: ");
        String phoneNumber = sc.nextLine();
        Address addr = this.readAddress();
        
        
        
        try{
            
            if(newUser instanceof Buyer){
                newUser = new Buyer(firstName, lastName, phoneNumber, addr);
            }
            
            if(newUser instanceof Seller){
                newUser = new Seller(firstName, lastName, phoneNumber, addr);
            }
            
            newUser.validData();
            return newUser;
            
        }catch(Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
        
       
    }
    
    private void listAllSellers(){
        //method that prints on screen all sellers 
        List<user> allUsers = context.getAllUsers();
        
        if(allUsers.size() > 0){
        
            System.out.println("All sellers are: ");
        
        }
        
        else{
            System.out.println("No sellers available!");
            return;
        }
        for(user usr: allUsers){
            if(usr instanceof Seller){
                System.out.println(String.valueOf(usr.getUserId()) + ": " + usr.getFullName());
            }
        }
    }
    
    private void listAllBuyers(){
        //method that prints on screen all sellers 
        List<user> allUsers = context.getAllUsers();
        
        if(allUsers.size() > 0){
        
            System.out.println("All Buyers are: ");
        
        }
        
        else{
            System.out.println("No Buyers available!");
            return;
        }
        for(user usr: allUsers){
            if(usr instanceof Buyer){
                System.out.println(String.valueOf(usr.getUserId()) + ": " + usr.getFullName());
            }
        }
    }
    
    @Override
    public void registerUser(){
        System.out.println("To choose an option press the shown key");
        System.out.println("0. Back.");
        System.out.println("1. Register a buyer.");
        System.out.println("2. Register a seller.");
        System.out.print("Choose option: ");
        
        int option = sc.nextInt();
        
        user newUser = null;
        
        switch(option){
            case 0:
                break;
            case 1:
                newUser = new Buyer();
                newUser = readUser(newUser);
                break;
            case 2:
                newUser = new Seller();
                newUser = readUser(newUser);
                break;
        }
        
        context.addUser(newUser);
        System.out.println("AIIC");
    }
 
    private void addObjectToAuction() throws Exception{
        System.out.println("Insert the id of the object desired");
        auctionObject obj;
        Auction auc;
        
        for(auctionObject object : context.getAllObjectsForSale()){
            System.out.println(object.getObjectId());
        }
        
        int id = sc.nextInt();
        sc.nextLine();
        try{
            obj = context.getObjectById(id);
        }catch(Exception e){
            throw e;
        }
        
        System.out.println("Insert the name of the auction ");
        
        
        context.listAvailableAuctions();
        
        String name = sc.nextLine();
        try{
            auc = context.getAuctionByName(name);
        }catch(Exception e){
            throw e;
        }
        
        auc.addObjectToAuction(obj);
       
        
        
    }
    
    private void makeBidOnObject() throws Exception{
        //makes a bid on the object
        Buyer buy;
        Auction auc;
        System.out.println("The id of the buyer is:");
        System.out.println("Available ids for buyer");
        
        this.listAllBuyers();
        
        System.out.println("Introduce the id ");
        int idBuyer = sc.nextInt();
        sc.nextLine();
        
        try{
            buy = context.getBuyerById(idBuyer);
        }catch(Exception e){
            throw e;
        }
        
        System.out.println("Insert the name of the auction ");
        
        
        for(Auction auction : context.getAuctionList()){
            System.out.println(auction.getName());
        }
        
        String name = sc.nextLine();
        try{
            auc = context.getAuctionByName(name);
        }catch(Exception e){
            throw e;
        }
        
        System.out.println("Insert the price of the bidding price");
        float price = sc.nextFloat();
        
        System.out.println("Available objects: ");
        
        System.out.println(auc.toString());
        
        System.out.println("The selected object id is: ");
        int objectId = sc.nextInt();
        
        
        try{
            auctionObject object = auc.getObjectWithId(objectId);
            auc.makeBidOnObject(buy, price, object);
        }catch(Exception e){
            throw e;
        }
        
        
    }
    
    private void getInfoAboutBuyer() throws Exception{
        System.out.println("Available ids for buyer");
        
        this.listAllBuyers();
        
        System.out.println("Insert the id");
        int idBuyer = sc.nextInt();
        sc.nextLine();
        
        try{
            Buyer buy = context.getBuyerById(idBuyer);
            System.out.println(buy.toString());
        }catch(Exception e){
            throw e;
        }
    }
    
    private void listAuctionsAndDetails(){
        if(context.getAuctionList().size() == 0){
            System.out.println("No auctions!");
            return;
        }
        for(Auction auc : context.getAuctionList()){
            if(auc.isAvailable()){
                System.out.println(auc.toString());
            }
        }
    }
    
    private void listAllUsers(){
        this.listAllBuyers();
        this.listAllSellers();
    }
    
    private void listAllObjects(){
        for(auctionObject obj : context.getAllObjectsForSale()){
            if(obj.getSoldStatus() == false){
                System.out.println(obj.toString());
            }
        }
    }
    
    public void listAllOldAuctions(){
        for(Auction auc : context.getAuctionList()){
            if(auc.isAvailable() == false){
                System.out.println(auc.toString());
            }
        }
    }
    
    @Override
    public void showMenu(){
        //method that shows the menu in console
        int option = -1;
        
        
        while(option != 0){
            context.finishAllOldAuctions(); //close all old transactions
            System.out.println("To choose an option press the shown key");
            System.out.println("0: Exit");
            System.out.println("1. Register a new user.");
            System.out.println("2: Register a new object");
            System.out.println("3: Add a new auction");
            System.out.println("4: Add object to auction");//de aici in jos necesita testare
            System.out.println("5: Make bid on object");
            System.out.println("6: Get information about a buyer");
            System.out.println("7: List all availables auctions");
            System.out.println("8: List all users");
            System.out.println("9: List all available objects for sale");
            System.out.println("10: List all old auctions and details");
            System.out.print("Press the option");
            option = sc.nextInt();
            sc.nextLine();
            
            try{
                switch (option){
                    case 0:
                        return;
                    case 1:
                        this.registerUser();
                        break;
                    case 2:
                        this.registerObject();
                        break;
                    case 3:
                        this.registerAuction();
                        break;

                    case 4:
                        this.addObjectToAuction();
                        break;
                    case 5:
                        this.makeBidOnObject();
                        break;
                    case 6:
                        this.getInfoAboutBuyer();
                        break;
                    case 7:
                        this.listAuctionsAndDetails();
                        break;
                        
                    case 8:
                        this.listAllUsers();
                        break;
                        
                    case 9:
                        this.listAllObjects();
                        break;
                        
                    case 10:
                        this.listAllOldAuctions();
                        break;
                }
            }catch(Exception e){
                System.out.println("Error on requested action!");
            }
        }
    }
        
    
    private int printObjectTypeOption() throws Exception{
        //method that prints what objects can be added
        
        System.out.println("1. Watch");
        
        int option = sc.nextInt();
        
        if(option != 1)
            throw new Exception("Invalid option!");
        
        return option;
    }
    
    private Watch readWatchObject() throws Exception{
        //float minPrice, float buyItNowPrice, Seller objectOwner,LocalDateTime dateAdded, Watch.MATERIAL watchMaterial, Watch.MECHANISM watchMechansim,String brandAndName;
        try{
            this.listAllSellers();
            System.out.print("The seller's id is: ");
            int idSeller = sc.nextInt();
            Seller seller = context.getSellerById(idSeller);
            System.out.print("The minimum price is: ");
            float minPrice = sc.nextFloat();
            System.out.print("The buying now price is: ");
            float buyItNow = sc.nextFloat();

            LocalDateTime date = this.readDate();
            
            int indexIterator = 0;
            
            for(Watch.MATERIAL mat: Watch.MATERIAL.values()){
                //for printing all the available materials
                System.out.println(String.valueOf(indexIterator) + "." + mat);
                indexIterator+=1;
            }
            
            System.out.print("Read option: ");
            int materialOption = sc.nextInt();
            
            if(materialOption < 0 || materialOption >= indexIterator){
                throw new Exception("Invalid Material!");
            }
            
            Watch.MATERIAL material = Watch.MATERIAL.values()[materialOption];
            
            indexIterator = 0;
            
            for(Watch.MECHANISM mat: Watch.MECHANISM.values()){
                //for printing all the available materials
                System.out.println(String.valueOf(indexIterator) + "." + mat);
                indexIterator+=1;
            }
            
            System.out.print("Read option: ");
            int mechanismOption = sc.nextInt();
            
            if(mechanismOption < 0 || mechanismOption >= indexIterator){
                throw new Exception("Invalid Material!");
            }
            
            Watch.MECHANISM mechanism = Watch.MECHANISM.values()[mechanismOption];
            
            System.out.println("The brand and name are: ");
            sc.nextLine();
            String brandAndName = sc.nextLine();
            
            return new Watch(minPrice, buyItNow, seller, date, material, mechanism, brandAndName);
            
            
        }catch(Exception e){
            throw e;
        }
        
        
        
    }
    
    @Override
    public void registerObject() throws Exception{
        //function that reads an object
        auctionObject obj = null;
        try{
            System.out.println("To choose an option press the shown key");
            int option = printObjectTypeOption();
            
            switch(option){
                case 1:
                    obj = readWatchObject();
                    break;
            }
            
            
        }catch(Exception e){
            throw e;
        }
        
        context.addObjectForSale(obj);
    }

    @Override
    public void loadData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public LocalDateTime readDate() throws Exception{
        try{
            System.out.print("The day: ");
            int dayBeginning = sc.nextInt();
            System.out.print("The month: ");
            int monthBeginning = sc.nextInt();
            System.out.print("The year: ");
            int yearBeginning = sc.nextInt();

            LocalDateTime time1 = LocalDateTime.of(yearBeginning, monthBeginning, dayBeginning, 0, 0);
            return time1;
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    public void registerAuction() throws IllegalArgumentException{
        System.out.print("Type the name of auction: ");
        String auctionName = sc.nextLine();


        
        
        try{
            System.out.println("The date beginning the auction");
            LocalDateTime time1 = readDate();
            System.out.println("The date ending the auction");
            LocalDateTime time2 = readDate();
            Auction acc = new Auction(time1, time2, auctionName);
            acc.validateData();
            context.addAuction(acc);
            System.out.println("as");
        }catch(Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }

    }

    
}
