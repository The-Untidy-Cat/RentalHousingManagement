/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.theuntidycat.rhm.model;

/**
 *
 * @author SHeroAnh
 */
public class PieChartDataset {
    public String key;
    public double value;
    public PieChartDataset (){
        key = "";
        value = 0;
    }
    public PieChartDataset (String tKey, double tValue){
        key = tKey;
        value = tValue;
    }
}
