package com.ulxsth.customitems;

import com.ulxsth.customitems.command.CustomGiveItemCommand;
import com.ulxsth.customitems.event.UseCircleSwordSlashEvent;
import com.ulxsth.customitems.event.UseHandGunEvent;
import com.ulxsth.customitems.util.ItemConfig;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomItemsPlugin extends JavaPlugin {
    private static CustomItemsPlugin instance;

    public static CustomItemsPlugin getInstance() {
        if(instance == null) {
            throw new IllegalStateException("instance is not initialized");
        }

        return instance;
    }

    @Override
    public void onEnable() {
        // インスタンスの初期化
        if(instance != null) {
            throw new IllegalStateException("instance is already initialized");
        }
        instance = this;

        // コンフィグファイルの初期化
        if(!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        ItemConfig.initConfig();

        // コマンドの登録
        getCommand("cgive").setExecutor(new CustomGiveItemCommand());

        // イベントリスナーの登録
        getServer().getPluginManager().registerEvents(new UseCircleSwordSlashEvent(), this);
        getServer().getPluginManager().registerEvents(new UseHandGunEvent(), this);
    }
}
