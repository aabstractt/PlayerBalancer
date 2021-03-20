package com.ithetrollidk.playerbalancer.priority;

import com.ithetrollidk.playerbalancer.priority.normal.NormalPriority;
import com.ithetrollidk.playerbalancer.priority.random.RandomFillerPriority;
import com.ithetrollidk.playerbalancer.priority.random.RandomLowestPriority;
import com.ithetrollidk.playerbalancer.priority.random.RandomPriority;
import com.ithetrollidk.playerbalancer.server.ServerException;
import com.ithetrollidk.playerbalancer.server.ServerGroupStorage;

import java.security.SecureRandom;
import java.util.List;

public class PriorityHandler {

    private static final SecureRandom instance = new SecureRandom();

    private static final String NORMAL = "NORMAL";
    private final static String LOWEST = "LOWEST";
    private final static String FILLER = "FILLER";
    private final static String RANDOM = "RANDOM";
    private static final String RANDOM_LOWEST = "RANDOM_LOWEST";
    private static final String RANDOM_FILLER = "RANDOM_FILLER";

    public static <T> T random(List<T> list) {
        return list.get(instance.nextInt(list.size()));
    }

    public Priority getServerPriority(ServerGroupStorage group) throws ServerException {
        for(PriorityEnum priority : PriorityEnum.values()) {
            if (group.getPriority().equals(priority.getValue())) {
                return priority.getPriority();
            }
        }

        throw new ServerException("Server priority not found...");
    }

    public enum PriorityEnum {
        NORMAL(PriorityHandler.NORMAL, new NormalPriority()),

        RANDOM(PriorityHandler.RANDOM, new RandomPriority()),
        RANDOM_LOWEST(PriorityHandler.RANDOM_LOWEST, new RandomLowestPriority()),
        RANDOM_FILLER(PriorityHandler.RANDOM_FILLER, new RandomFillerPriority());

        private final String value;

        private final Priority priority;

        PriorityEnum(String value, Priority priority) {
            this.value = value;

            this.priority = priority;
        }

        public String getValue() {
            return this.value;
        }

        public Priority getPriority() {
            return this.priority;
        }
    }
}