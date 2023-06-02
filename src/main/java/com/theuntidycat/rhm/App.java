package com.theuntidycat.rhm;

import com.theuntidycat.rhm.view.login.LoginView;

import com.theuntidycat.rhm.view.main.MainView;

import com.formdev.flatlaf.FlatLightLaf;

public class App {

    private static LoginView loginWindow = new LoginView();
    private static MainView mainWindow = new MainView();

    public static void main(String[] args) {
        FlatLightLaf.setup();
        loginWindow.run(mainWindow);
    }
}
