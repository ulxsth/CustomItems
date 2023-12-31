package com.ulxsth.customitems.model;

import de.tr7zw.changeme.nbtapi.NBT;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class GameItem {
    private final String label;
    private final Material material;
    private final String name;

    public GameItem(String materialId, String label, String name) {
        // IDからマテリアルを検索
        Material material = Material.getMaterial(materialId);
        if(material == null) {
            throw new IllegalArgumentException("materialId is invalid");
        }

        this.label = label;
        this.material = material;
        this.name = name;
    }

    public GameItem(String label, Material material, String name) {
        this.label = label;
        this.material = material;
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public Material getMaterial() {
        return material;
    }

    public String getName() {
        return name;
    }

    /**
     * ItemStackを生成する
     * @param amount
     * @return ItemStack
     */
    public ItemStack createItemStack(int amount) {
        ItemStack itemStack = new ItemStack(this.material, amount);

        // アイテムの名前を設定
        ItemMeta itemMeta = itemStack.getItemMeta();
        TextComponent itemName = Component.text(this.name);
        itemMeta.displayName(itemName);
        itemStack.setItemMeta(itemMeta);

        // NBTにデータを保存
        NBT.modify(itemStack, (nbt) -> {
            nbt.setString("label", this.label);
            nbt.setUUID("uuid", UUID.randomUUID());
        });

        return itemStack;
    }
}