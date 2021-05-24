package com.ithetrollidk.playerbalancer.priority.random;

import com.ithetrollidk.playerbalancer.priority.Priority;
import com.ithetrollidk.playerbalancer.priority.PriorityHandler;
import com.ithetrollidk.playerbalancer.server.ServerGroupStorage;
import dev.waterdog.waterdogpe.network.ServerInfo;

import java.util.ArrayList;
import java.util.List;

import static com.ithetrollidk.playerbalancer.priority.PriorityHandler.random;

public class RandomLowestPriority implements Priority {

    @Override
    public ServerInfo requestServer(ServerInfo targetServer, ServerGroupStorage group) {
        if (group == null) {
            return null;
        }

        List<ServerInfo> results = new ArrayList<>();

        int min = Integer.MAX_VALUE;

        for (ServerInfo server : group.getServersInfo()) {
            if (targetServer != null && server.getServerName().equals(targetServer.getServerName())) continue;

            int count = server.getPlayers().size();

            if (count <= min) {
                if (count < min) {
                    min = count;

                    results.clear();
                }

                results.add(server);
            }
        }

        if (results.isEmpty()) {
            return PriorityHandler.getInstance().requestServer(group.getParent());
        }

        return random(results);
    }
}