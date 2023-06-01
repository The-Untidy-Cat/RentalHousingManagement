/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.theuntidycat.rhm.model;

/**
 *
 * @author SHeroAnh
 */
public class BarChartDataset {
    public String key;
    public String unit;
    public double value;
    public BarChartDataset (){
        key = "";
        value = 0;
        unit = "";
    }
    public BarChartDataset (String tKey, double tValue, String tUnit){
        key = tKey;
        value = tValue;
        unit = tUnit;
    }
}
