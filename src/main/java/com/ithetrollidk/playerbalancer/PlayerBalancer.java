package com.ithetrollidk.playerbalancer;

import com.ithetrollidk.playerbalancer.config.Configuration;
import com.ithetrollidk.playerbalancer.listener.PreTransferListener;
import com.ithetrollidk.playerbalancer.ping.StatusStorage;
import com.ithetrollidk.playerbalancer.server.ServerException;
import com.ithetrollidk.playerbalancer.server.ServerStorage;
import dev.waterdog.waterdogpe.plugin.Plugin;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.CustomClassLoaderConstructor;

import java.io.*;

public class PlayerBalancer extends Plugin {

    private static PlayerBalancer instance;

    private Configuration configuration;

    public static PlayerBalancer getInstance() {
        return instance;
    }

    public Configuration getConfiguration() {
        return this.configuration;
    }

    @Override
    public void onEnable() {
        instance = this;

        this.saveResource("config.yml");

        try (InputStream in = new FileInputStream(this.getDataFolder().toString() + "/config.yml")) {
            this.configuration = (new Yaml(new CustomClassLoaderConstructor(Configuration.class.getClassLoader()))).loadAs(in, Configuration.class);

            ServerStorage.getInstance().init();

            StatusStorage.getInstance().init();

            new PreTransferListener();
        } catch (IOException | ServerException e) {
            e.printStackTrace();
        }
    }
}