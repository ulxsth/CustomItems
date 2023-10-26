package com.ulxsth.customitems.command;

import de.tr7zw.changeme.nbtapi.NBT;
import de.tr7zw.changeme.nbtapi.iface.ReadableItemNBT;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;
import java.util.function.Function;

public class DebugCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args[0].equals("checkid")) {
            if(!(sender instanceof Player player)) {
                sender.sendMessage("§c[ERROR] プレイヤーからのみ実行可能です");
                return true;
            }

            // itemStackの取得
            ItemStack itemStack = player.getInventory().getItemInMainHand();

            // NBTからデータを取得
            int id = NBT.get(itemStack, (Function<ReadableItemNBT, Integer>) nbt -> nbt.getInteger("id"));
            UUID uuid = NBT.get(itemStack, (Function<ReadableItemNBT, UUID>) nbt -> nbt.getUUID("uuid"));

            // データの表示
            sender.sendMessage("§a[INFO] id: " + id);
            sender.sendMessage("§a[INFO] uuid: " + uuid.toString());
        }

        return true;
    }
}
