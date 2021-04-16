package com.ithetrollidk.playerbalancer.priority.normal;

import com.ithetrollidk.playerbalancer.priority.Priority;
import com.ithetrollidk.playerbalancer.priority.PriorityHandler;
import com.ithetrollidk.playerbalancer.server.BungeeServer;
import com.ithetrollidk.playerbalancer.server.ServerGroupStorage;
import dev.waterdog.network.ServerInfo;

public class NormalPriority implements Priority {

    @Override
    public ServerInfo requestServer(ServerInfo targetServer, ServerGroupStorage group) {
        for (BungeeServer server : group.getServers().values()) {
            if (targetServer != null && server.getName().equals(targetServer.getServerName())) continue;

            if (server.getServerInfo().getPlayers().size() < server.getStatus().getMaxPlayers()) {
                return server.getServerInfo();
            }
        }

        return PriorityHandler.getInstance().requestServer(group.getParent());
    }
}