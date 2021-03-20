package com.ithetrollidk.playerbalancer.priority.random;

import com.ithetrollidk.playerbalancer.priority.Priority;
import com.ithetrollidk.playerbalancer.server.ServerGroupStorage;
import dev.waterdog.network.ServerInfo;

import static com.ithetrollidk.playerbalancer.priority.PriorityHandler.random;

public class RandomPriority implements Priority {

    @Override
    public ServerInfo requestServer(ServerInfo targetServer, ServerGroupStorage group) {
        return random(group.getServersInfo());
    }
}