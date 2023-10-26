package com.ulxsth.customitems.model;

import de.tr7zw.changeme.nbtapi.NBT;
import de.tr7zw.changeme.nbtapi.NBTItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GameItem {
    private final Material material;
    private final String name;
    private final int id;

    public GameItem(String materialId, String name, int id) {
        // IDからマテリアルを検索
        Material material = Material.getMaterial(materialId);
        if(material == null) {
            throw new IllegalArgumentException("materialId is invalid");
        }

        this.material = material;
        this.name = name;
        this.id = id;
    }

    public GameItem(Material material, String name, int id) {
        this.material = material;
        this.name = name;
        this.id = id;
    }

    public Material getMaterial() {
        return material;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    /**
     * ItemStackを生成する
     * @param amount
     * @return ItemStack
     */
    public ItemStack createItemStack(int amount) {
        ItemStack itemStack = new ItemStack(this.material, amount);
        NBTItem nbtItem = new NBTItem(itemStack);

        // アイテムの名前を設定
        ItemMeta itemMeta = itemStack.getItemMeta();
        TextComponent itemName = Component.text(this.name);
        itemMeta.displayName(itemName);
        itemStack.setItemMeta(itemMeta);

        // NBTにデータを保存
        nbtItem.setInteger("id", this.id);
        nbtItem.setUUID("uuid", java.util.UUID.randomUUID());

        return nbtItem.getItem();
    }
}