package com.ithetrollidk.playerbalancer.listener;

import com.ithetrollidk.playerbalancer.PlayerLocker;
import com.ithetrollidk.playerbalancer.priority.PriorityHandler;
import com.ithetrollidk.playerbalancer.server.ServerGroupStorage;
import com.ithetrollidk.playerbalancer.server.ServerStorage;
import dev.waterdog.waterdogpe.ProxyServer;
import dev.waterdog.waterdogpe.event.Event;
import dev.waterdog.waterdogpe.event.defaults.PreTransferEvent;
import dev.waterdog.waterdogpe.network.serverinfo.ServerInfo;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;

public class PreTransferListener extends Event {

    public PreTransferListener() {
        ProxyServer.getInstance().getEventManager().subscribe(PreTransferEvent.class, this::onPreTransferEvent);
    }

    public void onPreTransferEvent(PreTransferEvent ev) {
        ProxiedPlayer player = ev.getPlayer();

        if (player.hasPermission("playerbalancer.bypass")) return;
        if (PlayerLocker.isLocked(player)) return;

        ServerInfo serverInfo = ev.getTargetServer();

        ServerGroupStorage group = ServerStorage.getInstance().getGroupByServer(serverInfo);

        if (group == null) return;

        ServerInfo targetServerInfo = PriorityHandler.getInstance().requestServer(serverInfo, group);

        if (targetServerInfo == null) {
            targetServerInfo = serverInfo;
        }

        PlayerLocker.lock(player);
        ProxyServer.getInstance().getScheduler().scheduleDelayed(() -> PlayerLocker.unlock(player), 5 * 20);

        ev.setTargetServer(targetServerInfo);
    }
}