package com.ithetrollidk.playerbalancer;

import dev.waterdog.plugin.Plugin;

public class PlayerBalancer extends Plugin {

    private static PlayerBalancer instance;

    public static PlayerBalancer getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
    }
}