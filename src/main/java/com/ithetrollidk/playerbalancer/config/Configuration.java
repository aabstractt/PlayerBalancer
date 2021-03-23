package com.ithetrollidk.playerbalancer.config;

import java.util.Map;

public class Configuration {

    private Map<String, GroupList> groups;

    public Map<String, GroupList> getGroups() {
        return groups;
    }

    public void setGroups(Map<String, GroupList> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "groups=" + groups +
                '}';
    }
}