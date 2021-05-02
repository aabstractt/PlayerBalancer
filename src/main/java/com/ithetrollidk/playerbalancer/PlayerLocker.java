package com.ithetrollidk.playerbalancer;

import dev.waterdog.waterdogpe.player.ProxiedPlayer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PlayerLocker {

    private static final Set<String> storage = Collections.synchronizedSet(new HashSet<>());

    public static void lock(ProxiedPlayer player) {
        storage.add(player.getName().toLowerCase());
    }

    public static void unlock(ProxiedPlayer player) {
        storage.remove(player.getName().toLowerCase());
    }

    public static Boolean isLocked(ProxiedPlayer player) {
        return storage.contains(player.getName().toLowerCase());
    }
}
