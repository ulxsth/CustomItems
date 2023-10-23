package com.ulxsth.customitems.command;

import com.ulxsth.customitems.CustomItemsPlugin;
import com.ulxsth.customitems.model.GameItem;
import com.ulxsth.customitems.util.ItemConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/*
    ***Command Pattern***
    /cgive <item>: 実行者に指定されたアイテムを1つ渡す
    /cgive <item> <amount>: 実行者に指定されたアイテムを指定された個数渡す
    /cgive <item> <amount> <player>: 指定されたプレイヤーに指定されたアイテムを指定された個数渡す
*/

public class CustomGiveItemCommand implements CommandExecutor {
    private static final CustomItemsPlugin plugin = CustomItemsPlugin.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // プレイヤー以外からの実行を禁止
        if(!(sender instanceof Player player)) {
            sender.sendMessage("§c[ERROR] プレイヤーからのみ実行可能です");
            return true;
        }

        // 引数の数が足りなければエラーを返す
        if(args.length < 1) {
            return false;
        }

        // アイテムの検索処理
        String itemId = args[0];
        GameItem gameItem;
        try {
            gameItem = ItemConfig.getItemByLabel(itemId);
        } catch(IllegalArgumentException e) {
            sender.sendMessage("§c[ERROR] アイテムが見つかりません");
            return true;
        }

        // 個数の設定
        int amount = 1;
        if(args.length >= 2) {
            try {
                amount = Integer.parseInt(args[1]);
            } catch(NumberFormatException e) {
                sender.sendMessage("§c[ERROR] 個数は整数で指定してください");
                return true;
            }
        }

        // プレイヤーの検索処理
        if(args.length >= 3) {
            String playerName = args[2];
            player = plugin.getServer().getPlayer(playerName);

            // オンラインプレイヤーに存在しなければエラーを返す
            if(player == null) {
                sender.sendMessage("§c[ERROR] プレイヤーが見つかりません");
                return true;
            }
        }

        // アイテムを渡す処理
        ItemStack itemStack = gameItem.createItemStack(amount);

        PlayerInventory inventory = player.getInventory();
        inventory.addItem(ItemConfig.getItemByLabel(itemId).createItemStack(amount));

        if(!sender.equals(player)) player.sendMessage("§a[INFO] アイテムを受け取りました");
        sender.sendMessage("§a[INFO] アイテムを渡しました");
        return true;
    }
}
