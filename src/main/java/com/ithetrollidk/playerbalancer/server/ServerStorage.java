package com.ithetrollidk.playerbalancer.server;

import com.ithetrollidk.playerbalancer.PlayerBalancer;
import dev.waterdog.network.ServerInfo;
import dev.waterdog.utils.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerStorage {

    private static final ServerStorage instance = new ServerStorage();

    private final List<ServerGroupStorage> groups = new ArrayList<>();

    public static ServerStorage getInstance() {
        return instance;
    }

    @SuppressWarnings("unchecked")
    public void init() throws ServerException {
        Configuration config = PlayerBalancer.getInstance().getConfig();

        if (!config.exists("groups")) {
            throw new ServerException("Groups section not found");
        }

        ((Map<String, Map<String, Object>>) config.get("groups")).forEach((key, value) -> {
            Map<String, BungeeServer> servers = new HashMap<>();

            for (String serverName : (List<String>) value.get("servers")) {
                servers.put(serverName, new BungeeServer(key, serverName));
            }

            this.groups.add(new ServerGroupStorage(key, (Integer) value.getOrDefault("priority", 0), servers));
        });
    }

    public ServerGroupStorage getGroup(String groupName) {
        for (ServerGroupStorage group : this.groups) {
            if (!group.getName().equalsIgnoreCase(groupName)) continue;

            return group;
        }

        return null;
    }

    public ServerGroupStorage getGroupByServer(ServerInfo serverInfo) {
        for (ServerGroupStorage groupStorage : this.groups) {
            if (!groupStorage.containsServer(serverInfo.getServerName())) continue;

            return groupStorage;
        }

        return null;
    }
}