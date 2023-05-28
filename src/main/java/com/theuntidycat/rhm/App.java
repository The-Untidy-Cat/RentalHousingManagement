package com.theuntidycat.rhm;

import com.theuntidycat.rhm.view.LoginView;

import com.theuntidycat.rhm.view.MainView;

import com.formdev.flatlaf.FlatLightLaf;

public class App {
    private static LoginView loginWindow = new LoginView();
    private static MainView mainWindow = new MainView();
    
    public static void main(String[] args) {
        FlatLightLaf.setup(); 
        loginWindow.run(mainWindow);
        
    }
}


