package dev.gurei.listener;

import com.github.retrooper.packetevents.event.PacketListenerAbstract;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.ConnectionState;
import dev.gurei.player.PlayerData;
import dev.gurei.AntiCheatAPI;

public class PacketListener extends PacketListenerAbstract {

    public PacketListener() {
        super(PacketListenerPriority.LOW);
    }

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getConnectionState() != ConnectionState.PLAY) return;
        final PlayerData player = AntiCheatAPI.getInstance().getPlayerDataMap().getOrDefault(event.getUser(), null);
        if (player == null) return;

        player.getDetectorManager().onPacketReceive(event);
    }

    @Override
    public void onPacketSend(PacketSendEvent event) {
        if (event.getConnectionState() != ConnectionState.PLAY) return;
        final PlayerData player = AntiCheatAPI.getInstance().getPlayerDataMap().getOrDefault(event.getUser(), null);
        if (player == null) return;

        player.getDetectorManager().onPacketSend(event);
    }
}
