package com.ithetrollidk.playerbalancer.config;

import com.ithetrollidk.playerbalancer.server.BungeeServer;

import java.util.List;

public class GroupList {

    private String priority;

    private List<String> servers;

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getPriority() {
        return this.priority;
    }

    public List<String> getServers() {
        return this.servers;
    }

    public void setServers(List<String> servers) {
        this.servers = servers;
    }

    @Override
    public String toString() {
        return "GroupList{" +
                "priority='" + priority + '\'' +
                ", servers=" + servers +
                '}';
    }
}