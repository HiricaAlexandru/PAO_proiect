/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pao_proiect;

/**
 *
 * @author carja
 */
public interface UserInterface {
    
    Context context = Context.getInstance();
    
    public void registerUser();
    public void registerObject() throws Exception;
    public void registerAuction() throws Exception;
    public void showMenu();
    public void loadData();
}
