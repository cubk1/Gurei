package dev.gurei.player;

import com.github.retrooper.packetevents.protocol.player.User;
import dev.gurei.manager.TeleportManager;
import dev.gurei.manager.DetectorManager;

import java.util.UUID;

public class PlayerData {
    private final User user;
    private final UUID uniqueId;
    private final DetectorManager detectorManager;
    private final TeleportManager teleportManager;

    public PlayerData(User user) {
        this.user = user;
        this.uniqueId = user.getUUID();
        this.detectorManager = new DetectorManager(this);
        this.teleportManager = new TeleportManager(this);
    }

    public TeleportManager getTeleportManager() {
        return teleportManager;
    }

    public DetectorManager getDetectorManager() {
        return detectorManager;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public User getUser() {
        return user;
    }
}
