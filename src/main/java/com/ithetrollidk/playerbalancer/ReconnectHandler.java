package com.ithetrollidk.playerbalancer;

import com.ithetrollidk.playerbalancer.priority.PriorityHandler;
import com.ithetrollidk.playerbalancer.server.ServerGroupStorage;
import com.ithetrollidk.playerbalancer.server.ServerStorage;
import dev.waterdog.network.ServerInfo;
import dev.waterdog.player.ProxiedPlayer;
import dev.waterdog.utils.types.IReconnectHandler;

public class ReconnectHandler implements IReconnectHandler {

    @Override
    public ServerInfo getFallbackServer(ProxiedPlayer proxiedPlayer, ServerInfo serverInfo, String s) {
        ServerGroupStorage group = ServerStorage.getInstance().getGroupByServer(serverInfo);

        if (group == null) {
            group = ServerStorage.getInstance().getDefaultGroup();
        }

        ServerGroupStorage groupParent = group.getParent();

        if (groupParent == null) {
            groupParent = group;
        }

        return PriorityHandler.getInstance().requestServer(groupParent);
    }
}
