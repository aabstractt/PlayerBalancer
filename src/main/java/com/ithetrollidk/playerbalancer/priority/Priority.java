package com.ithetrollidk.playerbalancer.priority;

import com.ithetrollidk.playerbalancer.server.ServerGroupStorage;
import dev.waterdog.network.ServerInfo;

public interface Priority {

    ServerInfo requestServer(ServerInfo targetServer, ServerGroupStorage group);
}
