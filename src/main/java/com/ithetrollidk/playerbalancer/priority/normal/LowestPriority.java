package com.ithetrollidk.playerbalancer.priority.normal;

import com.ithetrollidk.playerbalancer.priority.Priority;
import com.ithetrollidk.playerbalancer.server.ServerGroupStorage;
import dev.waterdog.network.ServerInfo;

public class LowestPriority implements Priority {

    @Override
    public ServerInfo requestServer(ServerInfo targetServer, ServerGroupStorage group) {
        ServerInfo betterServer = null;

        for (ServerInfo serverInfo : group.getServersInfo()) {
            if (betterServer == null) {
                betterServer = serverInfo;

                continue;
            }

            if (serverInfo.getPlayers().size() > betterServer.getPlayers().size()) continue;

            betterServer = serverInfo;
        }

        return betterServer;
    }
}