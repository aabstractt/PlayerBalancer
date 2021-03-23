package com.ithetrollidk.playerbalancer.server;

import com.ithetrollidk.playerbalancer.PlayerBalancer;
import com.ithetrollidk.playerbalancer.config.Configuration;
import com.ithetrollidk.playerbalancer.priority.PriorityHandler;
import dev.waterdog.ProxyServer;
import dev.waterdog.network.ServerInfo;

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

    public void init() throws ServerException {
        Configuration configuration = PlayerBalancer.getInstance().getConfiguration();

        if (configuration.getGroups().isEmpty()) {
            throw new ServerException("Groups section not found");
        }

        configuration.getGroups().forEach((name, group) -> {
            Map<String, BungeeServer> servers = new HashMap<>();

            for (String server : group.getServers()) servers.put(server, new BungeeServer(name, server));

            this.groups.add(new ServerGroupStorage(name, group.getPriority(), servers));
        });

        ProxyServer.getInstance().setJoinHandler(proxiedPlayer -> PriorityHandler.getInstance().requestServer(ServerStorage.getInstance().getDefaultGroup()));
    }

    public List<ServerGroupStorage> getGroups() {
        return this.groups;
    }

    public ServerGroupStorage getDefaultGroup() {
        return this.getGroup(PlayerBalancer.getInstance().getConfiguration().getDefaultGroup());
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