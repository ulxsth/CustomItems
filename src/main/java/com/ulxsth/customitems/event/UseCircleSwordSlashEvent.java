package com.ulxsth.customitems.event;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class UseCircleSwordSlashEvent implements Listener {
    private static final String ITEM_NAME = "circle_sword";
    private static final int EFFECT_RANGE = 3;

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (
            item != null
            && item.getItemMeta().displayName().equals(ITEM_NAME)
            && event.getAction().name().contains("RIGHT_CLICK")
        ) {
            World world = player.getWorld();
            Location center = player.getLocation();

            for (double i = 0; i < 2 * Math.PI; i += Math.PI / 8) { // 円周上の点を計算
                double x = center.getX() + EFFECT_RANGE * Math.cos(i); // エフェクトの半径
                double z = center.getZ() + EFFECT_RANGE * Math.sin(i);
                Location effectLocation = new Location(world, x, center.getY(), z);

                world.spawnParticle(Particle.SWEEP_ATTACK, effectLocation, 1); // 斬撃エフェクトを生成
                world.playSound(effectLocation, "minecraft:entity.player.attack.sweep", 1, 1); // サウンドを再生
            }
        }
    }
}
