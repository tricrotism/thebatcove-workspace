package com.tricrotism.core.utils;

import com.tricrotism.core.Core;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.tags.CustomItemTagContainer;
import org.bukkit.inventory.meta.tags.ItemTagType;

import java.util.List;
import java.util.UUID;

public class ItemUtils {

    public static NamespacedKey UNIQUE_ID = new NamespacedKey("thebatcove-core", "unique_id");

    private static void handleLore(List<String> lore) {
        for (int i = 0; i < lore.size(); ++i) {
            lore.set(i, lore.get(i));
        }
    }

    static void hideAttributes(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
    }

    public static ItemStack createItem(Material material, String name, List<String> lore, boolean showAttributes) {
        handleLore(lore);

        ItemStack item = new ItemStack(material);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);

        if (!showAttributes) {
            hideAttributes(item);
        }
        return item;
    }

    public  static void ensureUnique(ItemStack item) {
        setCustomTag(item, UNIQUE_ID, ItemTagType.STRING, UUID.randomUUID().toString());
    }

    public static <Z, T> void setCustomTag(ItemStack item, NamespacedKey key, ItemTagType type, T object) {
        ItemMeta meta = item.getItemMeta();
        container(meta).setCustomTag(key, type, object);
        item.setItemMeta(meta);
    }

    public static Object getCustomTag(ItemStack item, NamespacedKey key, ItemTagType type) {
        if (item != null && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            if (meta != null && meta.getCustomTagContainer().hasCustomTag(key, type)) {
                return meta.getCustomTagContainer().getCustomTag(key, type);
            }
        }
        return null;
    }

    static CustomItemTagContainer container(ItemMeta meta) {
        return meta.getCustomTagContainer();
    }
}
