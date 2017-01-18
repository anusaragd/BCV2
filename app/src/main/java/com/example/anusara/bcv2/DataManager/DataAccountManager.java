package com.example.anusara.bcv2.DataManager;

/**
 * Created by anusara on 20-Nov-16.
 */
public class DataAccountManager {
    private String username;

    private static DataAccountManager instance;

    public static DataAccountManager getInstance() {
        if(instance == null) {
            instance = new DataAccountManager();
        }
        return instance;
    }

    public DataAccountManager() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
