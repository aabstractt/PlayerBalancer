package com.ithetrollidk.playerbalancer.config;

import java.util.Map;

public class Configuration {

    private Map<String, GroupList> groups;

    private String defaultGroup;

    public Map<String, GroupList> getGroups() {
        return groups;
    }

    public void setGroups(Map<String, GroupList> groups) {
        this.groups = groups;
    }

    public void setDefaultGroup(String defaultGroup) {
        this.defaultGroup = defaultGroup;
    }

    public String getDefaultGroup() {
        return this.defaultGroup;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "groups=" + groups +
                '}';
    }
}