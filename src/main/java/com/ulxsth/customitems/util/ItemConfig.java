package com.ulxsth.customitems.util;

import com.ulxsth.customitems.CustomItemsPlugin;
import com.ulxsth.customitems.model.GameItem;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ItemConfig {
    private static final CustomItemsPlugin plugin = CustomItemsPlugin.getInstance();
    public static final String PATH = plugin.getDataFolder().getPath() + "/item.yml";

    private static YamlConfiguration config;

    private ItemConfig() {}

    public static void initConfig() {
        if(!new File(PATH).exists()) {
            plugin.saveResource("item.yml", false);
        }

        config = YamlConfiguration.loadConfiguration(new File(PATH));
    }

    public static GameItem getItemByLabel(String label) {
        initConfig();
        ConfigurationSection section = config.getConfigurationSection(label);
        if(section == null) {
            throw new IllegalArgumentException("cant find: " + label);
        }

        String itemLabel = section.getString("label");
        String materialId = section.getString("material");
        String name = section.getString("name");

        return new GameItem(materialId, itemLabel, name);
    }
}