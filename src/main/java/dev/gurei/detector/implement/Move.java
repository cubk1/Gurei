package dev.gurei.detector.implement;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerPosition;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerPositionAndRotation;
import dev.gurei.detector.DetectionType;
import dev.gurei.detector.Detector;
import dev.gurei.player.PlayerData;

public class Move extends Detector {
    private double x,y,z;

    public Move(PlayerData player) {
        super(player);
    }

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if(event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION){
            WrapperPlayClientPlayerPosition packet = new WrapperPlayClientPlayerPosition(event);

            x = packet.getPosition().getX();
            y = packet.getPosition().getY();
            z = packet.getPosition().getZ();

            flag(0);
        }
        if(event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION_AND_ROTATION){
            WrapperPlayClientPlayerPositionAndRotation packet = new WrapperPlayClientPlayerPositionAndRotation(event);

            x = packet.getPosition().getX();
            y = packet.getPosition().getY();
            z = packet.getPosition().getZ();

            flag(0);
        }
    }

    @Override
    public String getName() {
        return "Move";
    }

    @Override
    public String getDetail() {
        return "Illegal move: " + x + "," + y + "," +z;
    }

    @Override
    public DetectionType getType() {
        return DetectionType.SB;
    }
}
