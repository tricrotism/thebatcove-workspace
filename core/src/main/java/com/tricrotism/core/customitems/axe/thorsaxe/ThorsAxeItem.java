package com.tricrotism.core.customitems.axe.thorsaxe;

import com.tricrotism.core.utils.ItemUtils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.tags.ItemTagType;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to create the Tree Capitator item.
 *
 * @since 0.0.0
 */
public class ThorsAxeItem {

    public static NamespacedKey NAMESPACE_KEY = new NamespacedKey("thebatcove-core", "thors-axe");

    /**
     * Creates the Thor's Axe item and gives it to the player.
     *
     * @param player the player to give the item to.
     * @since 0.0.0
     */
    public static void createItem(Player player) {
        List<String> lore = new ArrayList<>();
        lore.add("§7§oThis axe can send lightning");
        lore.add("§7§odown from the sky!");

        ItemStack item = ItemUtils.createItem(Material.IRON_AXE, "§b§lThor's Axe", lore, false);

        ItemUtils.setCustomTag(item, NAMESPACE_KEY, ItemTagType.STRING, "true");
        ItemUtils.ensureUnique(item);

        player.getInventory().addItem(item);
    }

}

