package com.ulxsth.customitems.command;

import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DebugCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args[0].equals("nbt")) {
            if(!(sender instanceof Player player)) {
                sender.sendMessage("§c[ERROR] プレイヤーからのみ実行可能です");
                return true;
            }

            // itemStackの取得
            ItemStack itemStack = player.getInventory().getItemInMainHand();

            // NBTを表示
            NBTItem nbtItem = new NBTItem(itemStack);
            sender.sendMessage("[INFO] " + nbtItem.toString());

        } else {
            sender.sendMessage("§c[ERROR] 不明なコマンドです");
        }

        return true;
    }
}
