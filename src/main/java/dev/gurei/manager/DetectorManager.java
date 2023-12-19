package dev.gurei.manager;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import dev.gurei.detector.Detector;
import dev.gurei.detector.implement.Block;
import dev.gurei.detector.implement.Combat;
import dev.gurei.detector.implement.Move;
import dev.gurei.player.PlayerData;

import java.util.HashMap;
import java.util.Map;

public class DetectorManager {

    private final Map<Class<? extends Detector>, Detector> detectorMap = new HashMap<>();

    public DetectorManager(PlayerData player) {
        registerDetector(
                new Block(player),
                new Combat(player),
                new Move(player)
        );
    }

    public void registerDetector(Detector... detectors) {
        for (Detector detector : detectors) {
            final Class<? extends Detector> detectorClass = detector.getClass();
            if (!detectorMap.containsKey(detectorClass)) {
                detectorMap.put(detectorClass, detector);
            }
        }
    }

    public void onPacketReceive(PacketReceiveEvent event) {
        detectorMap.values().forEach(detector -> {
            if (detector.isEnabled())
                detector.onPacketReceive(event);
        });
    }

    public void onPacketSend(PacketSendEvent event) {
        detectorMap.values().forEach(detector -> {
            if (detector.isEnabled())
                detector.onPacketSend(event);
        });
    }
}
