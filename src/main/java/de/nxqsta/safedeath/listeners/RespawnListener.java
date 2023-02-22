package de.nxqsta.safedeath.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.Objects;

public class RespawnListener implements Listener {

    /** Gives the Player his last Death Location
     *
     * @param event Fired when a Player respawns
     */
    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        Location lastDeathLocation = Objects.requireNonNull(player.getLastDeathLocation());
        player.sendMessage("Dein Loot: XYZ: " + lastDeathLocation.getX() + " " + lastDeathLocation.getY() + " " + lastDeathLocation.getZ());
    }

}
