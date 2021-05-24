package com.ithetrollidk.playerbalancer.priority.random;

import com.ithetrollidk.playerbalancer.priority.Priority;
import com.ithetrollidk.playerbalancer.priority.PriorityHandler;
import com.ithetrollidk.playerbalancer.server.ServerGroupStorage;
import dev.waterdog.waterdogpe.network.ServerInfo;

import static com.ithetrollidk.playerbalancer.priority.PriorityHandler.random;

public class RandomPriority implements Priority {

    @Override
    public ServerInfo requestServer(ServerInfo targetServer, ServerGroupStorage group) {
        if (group == null) {
            return null;
        }

        if (group.getServersInfo().isEmpty()) {
            return PriorityHandler.getInstance().requestServer(group.getParent());
        }

        return random(group.getServersInfo());
    }
}