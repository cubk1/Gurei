package dev.gurei.detector.implement;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerBlockPlacement;
import dev.gurei.detector.DetectionType;
import dev.gurei.detector.Detector;
import dev.gurei.player.PlayerData;

public class Block extends Detector {
    public Block(PlayerData player) {
        super(player);
    }

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if(event.getPacketType() == PacketType.Play.Client.PLAYER_BLOCK_PLACEMENT){
            WrapperPlayClientPlayerBlockPlacement packet = new WrapperPlayClientPlayerBlockPlacement(event);

            if(packet.getBlockPosition().x != -1){
                flag(0);
                event.setCancelled(true);
            }
        }
    }

    @Override
    public String getName() {
        return "Block";
    }

    @Override
    public String getDetail() {
        return "Illegal place";
    }

    @Override
    public DetectionType getType() {
        return DetectionType.SB;
    }
}
