package dev.gurei;

import org.bukkit.plugin.java.JavaPlugin;

public class Gurei extends JavaPlugin {

    @Override
    public void onLoad() {
        AntiCheatAPI.getInstance().onLoad(this);
    }

    @Override
    public void onEnable() {
        AntiCheatAPI.getInstance().onEnable(this);
    }

    @Override
    public void onDisable() {
        AntiCheatAPI.getInstance().onDisable(this);
    }
}
