package com.ithetrollidk.playerbalancer.listener;

import com.ithetrollidk.playerbalancer.priority.PriorityHandler;
import com.ithetrollidk.playerbalancer.server.ServerGroupStorage;
import com.ithetrollidk.playerbalancer.server.ServerStorage;
import dev.waterdog.ProxyServer;
import dev.waterdog.event.Event;
import dev.waterdog.event.defaults.PreTransferEvent;
import dev.waterdog.network.ServerInfo;
import dev.waterdog.player.ProxiedPlayer;

public class PreTransferListener extends Event {

    public PreTransferListener() {
        ProxyServer.getInstance().getEventManager().subscribe(PreTransferEvent.class, this::onPreTransferEvent);
    }

    public void onPreTransferEvent(PreTransferEvent ev) {
        ProxiedPlayer player = ev.getPlayer();

        if (player.hasPermission("playerbalancer.bypass")) return;

        ServerInfo serverInfo = ev.getTargetServer();

        ServerGroupStorage group = ServerStorage.getInstance().getGroupByServer(serverInfo);

        if (group == null) return;

        ServerInfo targetServerInfo = PriorityHandler.getInstance().requestServer(serverInfo, group);

        if (targetServerInfo == null) {
            targetServerInfo = serverInfo;
        }

        ev.setTargetServer(targetServerInfo);
    }
}