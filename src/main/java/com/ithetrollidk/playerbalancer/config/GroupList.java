package com.ithetrollidk.playerbalancer.config;

import java.util.List;

public class GroupList {

    private String priority;

    private List<String> servers;

    private String parent;

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

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getParent() {
        return this.parent;
    }

    @Override
    public String toString() {
        return "GroupList{" +
                "priority='" + priority + '\'' +
                ", servers=" + servers +
                '}';
    }
}