package dev.gurei.listener;

import com.github.retrooper.packetevents.event.PacketListenerAbstract;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.event.UserDisconnectEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.player.User;
import dev.gurei.player.PlayerData;
import dev.gurei.AntiCheatAPI;

public class ConnectionListener extends PacketListenerAbstract {

    @Override
    public void onPacketSend(PacketSendEvent event) {
        super.onPacketSend(event);

        if (event.getPacketType() != PacketType.Login.Server.LOGIN_SUCCESS)
            return;
        final User user = event.getUser();
        event.getTasksAfterSend().add(() ->
                AntiCheatAPI.getInstance().getPlayerDataMap()
                        .put(user, new PlayerData(user)));
    }

    @Override
    public void onUserDisconnect(UserDisconnectEvent event) {
        super.onUserDisconnect(event);
        if (!AntiCheatAPI.getInstance().getPlayerDataMap().containsKey(event.getUser()))
            return;
        AntiCheatAPI.getInstance().getPlayerDataMap().remove(event.getUser());
    }
}
