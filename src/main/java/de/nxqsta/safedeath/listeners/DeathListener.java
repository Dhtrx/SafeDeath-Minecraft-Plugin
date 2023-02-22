package de.nxqsta.safedeath.listeners;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.block.Chest;

import java.util.*;

public class DeathListener implements Listener {

    private Location deathLocation;
    private World world;
    private final List<ItemStack> itemsOne = new ArrayList<>();
    private final List<ItemStack> itemsTwo = new ArrayList<>();

    /** Saves all necessary information to spawn the death chests
     *
     * @param event Fired when an Entity receives damage
     */
    @EventHandler
    public void onDamage(EntityDamageEvent event) {

        if (event.getEntity() instanceof Player player) {

            player = (Player) event.getEntity();
            double damage = event.getDamage();

            if(player.getHealth() - damage <= 0) {
                //Prepare chest spawn
                this.deathLocation = player.getLocation();
                this.world = player.getWorld();

                //Fill up the Lists of Items of the PlayerInventory
                List<ItemStack> playerInventory = Arrays.stream(player.getInventory().getContents()).filter(Objects::nonNull).toList();
                for (ItemStack itemStack : playerInventory) {
                    if (itemsOne.size() < 27) {
                        itemsOne.add(itemStack);
                    } else {
                        itemsTwo.add(itemStack);
                    }
                }

                //Clear Player Inventory
                player.getInventory().clear();
            }
        }
    }

    /** Spawns the death chests and fills the chests with the PlayerInventory
     * Only the player that died can access the chests
     * @param event Fired when a Player dies
     */
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if(this.deathLocation != null) {

            //Get Blocks where the chests will be created
            World world = Objects.requireNonNull(Bukkit.getWorld(this.world.getName()));
            Block blockOne = world.getBlockAt(deathLocation);
            Block blockTwo = world.getBlockAt(deathLocation.getBlockX() + 1, deathLocation.getBlockY(), deathLocation.getBlockZ());

            //Create the chests
            blockOne.setType(Material.CHEST);
            blockTwo.setType(Material.CHEST);
            Chest deathChestOne = (Chest) blockOne.getState();
            Chest deathChestTwo = (Chest) blockTwo.getState();

            //Add inventory to death chests
            deathChestOne.getInventory().setContents(itemsOne.toArray(new ItemStack[0]));
            deathChestTwo.getInventory().setContents(itemsTwo.toArray(new ItemStack[0]));

            //Clear item lists
            itemsOne.clear();
            itemsTwo.clear();

        }
    }
}
