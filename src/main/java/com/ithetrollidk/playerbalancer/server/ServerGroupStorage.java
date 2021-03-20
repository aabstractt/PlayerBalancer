package com.ithetrollidk.playerbalancer.server;

import java.util.Map;

public class ServerGroupStorage {

    private final String name;

    private final Integer priority;

    private final Map<String, BungeeServer> servers;

    public ServerGroupStorage(String name, Integer priotory, Map<String, BungeeServer> servers) {
        this.name = name;

        this.priority = priotory;

        this.servers = servers;
    }

    public String getName() {
        return name;
    }

    public Boolean containsServer(String serverName) {
        return this.servers.containsKey(serverName);
    }

    public Map<String, BungeeServer> getServers() {
        return servers;
    }
}