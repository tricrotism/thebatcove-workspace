package com.tricrotism.core.customitems.axe.treecapitator;

import com.tricrotism.core.utils.ItemUtils;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.meta.tags.ItemTagType;

public class TreeCapitatorHandler implements Listener {

    /**
     * Handles the TreeCapitator event, which is when a player breaks a log block with an axe, it'll break the entire tree.
     *
     * @param event
     */
    @EventHandler
    public void handleTreeCapitatior(BlockBreakEvent event) {
        if (ItemUtils.getCustomTag(event.getPlayer().getInventory().getItemInMainHand(), TreeCapitatorItem.NAMESPACE_KEY, ItemTagType.STRING).equals("true")) {
            handleTreeBreak(event.getBlock());
        }
    }

    public void handleTreeBreak(Block block) {
        if (!block.getType().toString().contains("LOG")) {
            return;
        }

        block.breakNaturally();

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    if (x == 0 && y == 0 && z == 0) continue;
                    Block relative = block.getRelative(x, y, z);
                    if (relative.getType().toString().contains("LOG")) {
                        handleTreeBreak(relative);
                    }
                }
            }
        }

        Block above = block.getRelative(0, 1, 0);
        Block below = block.getRelative(0, -1, 0);
        if (above.getType().toString().contains("LOG")) {
            handleTreeBreak(above);
        }
        if (below.getType().toString().contains("LOG")) {
            handleTreeBreak(below);
        }
    }

}
