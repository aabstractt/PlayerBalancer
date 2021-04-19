package com.ithetrollidk.playerbalancer.ping;

import com.ithetrollidk.playerbalancer.Callback;
import com.ithetrollidk.playerbalancer.server.BungeeServer;
import com.ithetrollidk.playerbalancer.server.ServerGroupStorage;
import com.ithetrollidk.playerbalancer.server.ServerStorage;
import dev.waterdog.ProxyServer;
import dev.waterdog.network.ServerInfo;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.util.concurrent.TimeUnit;

public class StatusStorage {

    private static final StatusStorage instance = new StatusStorage();

    public static StatusStorage getInstance() {
        return instance;
    }

    public void init() {
        ProxyServer.getInstance().getLogger().info(String.format("Starting the ping task, the interval is %s", 100));

        ProxyServer.getInstance().getScheduler().scheduleRepeating(() -> {
            for (ServerGroupStorage groupStorage : ServerStorage.getInstance().getGroups()) {
                for (BungeeServer bungeeServer : groupStorage.getServers().values()) {
                    this.update(bungeeServer);
                }
            }
        }, 100);
    }

    public void update(BungeeServer server) {
        this.ping(server.getServerInfo(), (status, throwable) -> {
            if (status == null) {
                status = new ServerStatus();
            }

            /*PlayerBalancer.getInstance().getLogger().info(String.format(
                    "Updated server %s, status: [Players: %s, Maximum Players: %s, Online: %s]",
                    server.getName(), status.getOnlineCount(), status.getMaxPlayers(), status.isOnline()
            ));*/

            server.setStatus(status);
        });
    }

    public void ping(ServerInfo server, Callback<ServerStatus> callback) {
        if (server == null) return;

        ProxyServer.getInstance().getScheduler().scheduleAsync(() -> server.ping(1000, TimeUnit.MILLISECONDS).whenComplete(((rakNetPong, throwable) -> {
            if (rakNetPong == null) {
                callback.done(null, throwable);

                return;
            }

            byte[] bytes = rakNetPong.getUserData();

            byte[] in = new byte[readVarInt(Unpooled.wrappedBuffer(bytes))];

            read(in, in.length, bytes);

            String[] data = new String(in).split(";");

            callback.done(new ServerStatus(Integer.parseInt(data[4]), Integer.parseInt(data[5])), throwable);
        })));
    }

    /**
     * Code by https://github.com/jamezrin/PlayerBalancer
     */
    private static void read(byte[] in, int len, byte[] bytes) {
        int n = 0;

        ByteArrayInputStream out = new ByteArrayInputStream(bytes);

        while (n < len) {
            int count = out.read(in, n, len - n);

            if (count < 0)
                try {
                    throw new EOFException();
                } catch (EOFException e) {
                    e.printStackTrace();
                }

            n += count;
        }
    }

    private static int readVarInt(ByteBuf in) {
        int i = 0;
        int j = 0;

        while (true) {
            int k = in.readByte();
            i |= (k & 0x7F) << j++ * 7;
            if (j > 5) throw new RuntimeException("VarInt too big");
            if ((k & 0x80) != 128) break;
        }

        return i;
    }
}