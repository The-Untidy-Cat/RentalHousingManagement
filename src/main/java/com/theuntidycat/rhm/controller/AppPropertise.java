/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.theuntidycat.rhm.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author SHeroAnh
 */
public class AppPropertise {

    private Path currentRelativePath = Paths.get("");
    private String filePath;
    private Properties data;

    public AppPropertise(){
        String s = currentRelativePath.toAbsolutePath().toString();
        filePath = s + "/app.propertise";
        File appConfig = new File(filePath);
        if (!appConfig.exists()) {
            try {
                initConfigFile();
            } catch (IOException io) {
                io.printStackTrace();
            }
        } else {
            data = readPropertiesFile(filePath);
        }
    }

    public Properties readPropertiesFile(String fileName) {
        FileInputStream fis = null;
        Properties prop = null;
        try {
            fis = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fis);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } 
        return prop;
    }
    
    public String getData(String key) {
        return (String)data.getProperty(key);
    }
    
    public void setData(String key, String value) {
        data.setProperty(key, value);
    }

    public void initConfigFile() throws IOException {
        //Instantiating the properties file
        Properties props = new Properties();
        //Populating the properties file
        props.put("email", "abc@gmail.com");
        props.put("password", "password");
        props.put("smtp_port", "465");
        props.put("smtp_host", "smtp.gmail.com");
        props.put("ssl", "true");
        //Instantiating the FileInputStream for output file

        FileOutputStream outputStream = new FileOutputStream(filePath);
        //Storing the properties file
        props.store(outputStream, "#");
    }
}
