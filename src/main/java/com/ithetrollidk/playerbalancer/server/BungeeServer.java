package com.ithetrollidk.playerbalancer.server;

public class BungeeServer {

    private final String group;

    private final String name;

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

    public String getName() {
        return this.name;
    }
}