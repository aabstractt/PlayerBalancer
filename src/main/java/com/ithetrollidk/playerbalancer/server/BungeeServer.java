package com.ithetrollidk.playerbalancer.server;

import com.ithetrollidk.playerbalancer.ping.ServerStatus;
import dev.waterdog.ProxyServer;
import dev.waterdog.network.ServerInfo;

public class BungeeServer {

    private final String group;
    private final String name;
    private ServerStatus status = null;

    public BungeeServer(String group, String name) {
        this.group = group;

        this.name = name;
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

    public ServerStatus getStatus() {
        return this.status;
    }

    public void setStatus(ServerStatus status) {
        this.status = status;
    }
}