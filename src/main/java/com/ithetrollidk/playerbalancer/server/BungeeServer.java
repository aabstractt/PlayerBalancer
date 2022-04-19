package com.ithetrollidk.playerbalancer.server;

import com.ithetrollidk.playerbalancer.ping.ServerStatus;
import dev.waterdog.waterdogpe.ProxyServer;
import dev.waterdog.waterdogpe.network.serverinfo.ServerInfo;

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