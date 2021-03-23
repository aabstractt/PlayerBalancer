package com.ithetrollidk.playerbalancer.server;

import dev.waterdog.network.ServerInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServerGroupStorage {

    private final String name;

    private final String priority;

    private final Map<String, BungeeServer> servers;

    public ServerGroupStorage(String name, String priority, Map<String, BungeeServer> servers) {
        this.name = name;

        this.priority = priority;

        this.servers = servers;
    }

    public String getName() {
        return name;
    }

    public String getPriority() {
        return this.priority;
    }

    public Boolean containsServer(String serverName) {
        return this.servers.containsKey(serverName);
    }

    public Map<String, BungeeServer> getServers() {
        return this.servers;
    }

    public List<ServerInfo> getServersInfo() {
        List<ServerInfo> servers = new ArrayList<>();

        for (BungeeServer server : this.servers.values()) {
            if (server.getStatus() == null || !server.getStatus().isOnline()) {
                continue;
            }

            servers.add(server.getServerInfo());
        }

        return servers;
    }
}