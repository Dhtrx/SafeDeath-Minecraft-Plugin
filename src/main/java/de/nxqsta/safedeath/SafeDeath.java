package de.nxqsta.safedeath;

import de.nxqsta.safedeath.listeners.DeathListener;
import de.nxqsta.safedeath.listeners.RespawnListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class SafeDeath extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        //Register Listeners
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new DeathListener(), this);
        pluginManager.registerEvents(new RespawnListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
