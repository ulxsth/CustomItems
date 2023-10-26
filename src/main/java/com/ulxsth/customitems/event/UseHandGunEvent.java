package com.ulxsth.customitems.event;

import com.ulxsth.customitems.CustomItemsPlugin;
import com.ulxsth.customitems.util.ItemConfig;
import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.iface.ReadableItemNBT;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.function.Function;

public class UseHandGunEvent implements Listener {
    private static final String ITEM_LABEL = "hand_gun";
    private static final int ITEM_ID = ItemConfig.getItemByLabel(ITEM_LABEL).getId();
    private static final CustomItemsPlugin plugin = CustomItemsPlugin.getInstance();

    private static final double DAMAGE = 3;
    private static final int AFFECT_RANGE = 100;

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
            Player player = event.getPlayer();
            ItemStack item = event.getItem();
            int itemId = NBT.get(item, (Function<ReadableItemNBT, Integer>) nbt -> nbt.getInteger("id"));

        if (
                itemId == ITEM_ID
                && event.getAction().name().contains("RIGHT_CLICK")
        ) {
            Location location = player.getEyeLocation();
            Vector direction = location.getDirection().normalize();
            boolean isAlreadyHit = false;

            for (double i = 0; i <= AFFECT_RANGE; i += 0.5) {
                if(isAlreadyHit) {
                    break;
                }

                Location checkLocation = location.clone().add(direction.clone().multiply(i));
                if (checkLocation.getBlock().getBoundingBox().contains(checkLocation.toVector())) {
                    // Stop checking when a non-air block is encountered
                    break;
                }

                for (Entity entity : checkLocation.getNearbyEntities(0.5, 0.5, 0.5)) {
                    if (entity instanceof LivingEntity && entity != player) {
                        ((LivingEntity) entity).damage(DAMAGE); // Apply damage
                        isAlreadyHit = true;
                        break;
                    }
                }
            }

            player.getWorld().playSound(location, Sound.ENTITY_FIREWORK_ROCKET_BLAST, 1, 1); // Play sound
            player.getWorld().spawnParticle(Particle.FLAME, location, 10, direction.getX(), direction.getY(), direction.getZ(), 0.1); // Spawn particles
        }
    }
}
