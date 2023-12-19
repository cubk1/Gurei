package dev.gurei.detector.implement;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientEntityAction;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import dev.gurei.detector.DetectionType;
import dev.gurei.detector.Detector;
import dev.gurei.player.PlayerData;

public class Combat extends Detector {
    public Combat(PlayerData player) {
        super(player);
    }

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if(event.getPacketType() == PacketType.Play.Client.INTERACT_ENTITY){
            WrapperPlayClientInteractEntity packet = new WrapperPlayClientInteractEntity(event);

            if(packet.getAction() == WrapperPlayClientInteractEntity.InteractAction.ATTACK){
                flag(0);
                event.setCancelled(true);
            }
        }
    }

    @Override
    public String getName() {
        return "Combat";
    }

    @Override
    public String getDetail() {
        return "Illegal attack";
    }

    @Override
    public DetectionType getType() {
        return DetectionType.SB;
    }
}
