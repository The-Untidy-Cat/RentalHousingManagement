/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.theuntidycat.rhm.model;

/**
 *
 * @author TTMC
 */
public class Invoice {
    public String ID;
    public String Period;
    public String Room;
    public int Money;
    public String Status;
    
    public Invoice(){
        ID = "";
        Period = "";
        Room = "";
        Money = 0;
        Status = "";
    }
}
