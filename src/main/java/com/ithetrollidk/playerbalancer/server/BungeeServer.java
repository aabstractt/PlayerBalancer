package com.ithetrollidk.playerbalancer.server;

import dev.waterdog.ProxyServer;
import dev.waterdog.network.ServerInfo;

public class BungeeServer {

    private final String group;
    private final String name;
    private final Integer maxPlayers;

    public BungeeServer(String group, String name, Integer maxPlayers) {
        this.group = group;

        this.name = name;

        this.maxPlayers = maxPlayers;
    }

    public ServerGroupStorage getGroup() {
        return ServerStorage.getInstance().getGroup(this.getGroupName());
    }

    public String getGroupName() {
        return this.group;
    }

    public ServerInfo getServerInfo() {
        return ProxyServer.getInstance().getServerInfo(this.getName());
    }

    public String getName() {
        return this.name;
    }

    public Integer getMaxPlayers() {
        return this.maxPlayers;
    }
}