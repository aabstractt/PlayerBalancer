package com.ithetrollidk.playerbalancer;

import com.ithetrollidk.playerbalancer.server.ServerException;
import com.ithetrollidk.playerbalancer.server.ServerStorage;
import dev.waterdog.plugin.Plugin;

public class PlayerBalancer extends Plugin {

    private static PlayerBalancer instance;

    public static PlayerBalancer getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        try {
            ServerStorage.getInstance().init();
        } catch (ServerException e) {
            e.printStackTrace();
        }
    }
}