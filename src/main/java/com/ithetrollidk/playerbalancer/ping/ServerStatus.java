package com.ithetrollidk.playerbalancer.ping;

public class ServerStatus {

    private final Integer onlineCount;
    private final Integer maxPlayers;
    private Boolean online = false;

    public ServerStatus() {
        this.onlineCount = 0;

        this.maxPlayers = 0;
    }

    public ServerStatus(Integer onlineCount, Integer maxPlayers) {
        this.onlineCount = onlineCount;

        this.maxPlayers = maxPlayers;

        this.online = true;
    }

    public Integer getOnlineCount() {
        return this.onlineCount;
    }

    public Integer getMaxPlayers() {
        return this.maxPlayers;
    }

    public Boolean isOnline() {
        return this.online;
    }

    @Override
    public String toString() {
        return "ServerStatus{" +
                "onlineCount=" + onlineCount +
                ", maxPlayers=" + maxPlayers +
                ", online=" + online +
                '}';
    }
}