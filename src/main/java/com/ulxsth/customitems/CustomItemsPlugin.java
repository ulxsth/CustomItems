package com.ulxsth.customitems;

import com.ulxsth.customitems.command.CustomGiveItemCommand;
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

        // コマンドの登録
        getCommand("cgive").setExecutor(new CustomGiveItemCommand());
    }
}
