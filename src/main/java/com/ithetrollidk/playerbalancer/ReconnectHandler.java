package com.ithetrollidk.playerbalancer;

import com.ithetrollidk.playerbalancer.priority.PriorityHandler;
import com.ithetrollidk.playerbalancer.server.ServerGroupStorage;
import com.ithetrollidk.playerbalancer.server.ServerStorage;
import dev.waterdog.waterdogpe.ProxyServer;
import dev.waterdog.waterdogpe.network.serverinfo.ServerInfo;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;
import dev.waterdog.waterdogpe.utils.types.IReconnectHandler;

public class ReconnectHandler implements IReconnectHandler {

    @Override
    public ServerInfo getFallbackServer(ProxiedPlayer proxiedPlayer, ServerInfo serverInfo, String s) {
        if (PlayerLocker.isLocked(proxiedPlayer)) { // Check if the player is locked to don't request other server again
            return null;
        }

        ServerGroupStorage group = ServerStorage.getInstance().getGroupByServer(serverInfo);

        if (group == null) {
            group = ServerStorage.getInstance().getDefaultGroup();
        }

        ServerGroupStorage parent = group.getParent();

        if (parent == null) {
            parent = group;
        }

        proxiedPlayer.sendMessage("§cThe server you were previously on went down, you have been connected to a fallback server");

        PlayerLocker.lock(proxiedPlayer);
        ProxyServer.getInstance().getScheduler().scheduleDelayed(() -> PlayerLocker.unlock(proxiedPlayer), 5 * 20);

        return PriorityHandler.getInstance().requestServer(parent);
    }
}