package com.ulxsth.customitems.event;

import com.ulxsth.customitems.CustomItemsPlugin;
import com.ulxsth.customitems.util.ItemConfig;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class UseHandGunEvent implements Listener {
    private static final String ITEM_LABEL = "hand_gun";
    private static final int ITEM_ID = ItemConfig.getItemByLabel(ITEM_LABEL).getId();
    private static final CustomItemsPlugin plugin = CustomItemsPlugin.getInstance();

    private static final int EFFECT_RANGE = 30;

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Location location = player.getEyeLocation();
        Vector direction = location.getDirection().normalize();

        if (
            item != null
            && item.getItemMeta().hasCustomModelData()
            && item.getItemMeta().getCustomModelData() == ITEM_ID
            && event.getAction().name().contains("RIGHT_CLICK")
        ) {
            // プレイヤーの向いている方向にエフェクトを発生させる
            for (double i = 0; i < EFFECT_RANGE; i += 0.5) {
                double x = direction.getX() * i;
                double y = direction.getY() * i;
                double z = direction.getZ() * i;

                player.getWorld().spawnParticle(Particle.FLAME, location.add(x, y, z), 1);
            }
        }
    }
}
