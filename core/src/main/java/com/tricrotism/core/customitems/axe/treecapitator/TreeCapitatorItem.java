package com.tricrotism.core.customitems.axe.treecapitator;

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
 * @since 0.0.0
 */
public class TreeCapitatorItem {

    public static NamespacedKey NAMESPACE_KEY = new NamespacedKey("thebatcove-core", "tree_capitator");

    /**
     * Creates the Tree Capitator item and gives it to the player.
     * @param player the player to give the item to.
     */
    public static void createItem(Player player) {
        List<String> lore = new ArrayList<>();
        lore.add("§7§oThis axe can chop down entire trees");
        lore.add("§7§oin one swing!");

        ItemStack item = ItemUtils.createItem(Material.WOODEN_AXE,  "§a§lTree Capitator",  lore,  false);

        ItemUtils.setCustomTag(item, NAMESPACE_KEY, ItemTagType.STRING, "true");
        ItemUtils.ensureUnique(item);

        player.getInventory().addItem(item);
    }

}
