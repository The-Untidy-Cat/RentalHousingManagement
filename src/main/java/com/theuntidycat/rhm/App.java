package com.theuntidycat.rhm;

import com.theuntidycat.rhm.view.login.LoginView;

import com.theuntidycat.rhm.view.main.MainView;

import com.formdev.flatlaf.FlatLightLaf;
import com.theuntidycat.rhm.controller.AppPropertiseController;
import java.io.IOException;

public class App {

    private static LoginView loginWindow;
    private static MainView mainWindow;

    public static void main(String[] args) {
        mainWindow = new MainView();
        loginWindow = new LoginView();
        try {
            new AppPropertiseController();
            FlatLightLaf.setup();
            loginWindow.run(mainWindow);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
