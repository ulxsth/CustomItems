package com.ulxsth.customitems.event;

import com.ulxsth.customitems.CustomItemsPlugin;
import com.ulxsth.customitems.util.ItemConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class UseHandGunEvent implements Listener {
    private static final String ITEM_LABEL = "hand_gun";
    private static final int ITEM_ID = ItemConfig.getItemById(ITEM_LABEL).getId();
    private static final CustomItemsPlugin plugin = CustomItemsPlugin.getInstance();

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (
            item != null
            && item.getItemMeta().hasCustomModelData()
            && item.getItemMeta().getCustomModelData() == ITEM_ID
            && event.getAction().name().contains("RIGHT_CLICK")
        ) {
            player.launchProjectile(org.bukkit.entity.Egg.class);
        }
    }
}
