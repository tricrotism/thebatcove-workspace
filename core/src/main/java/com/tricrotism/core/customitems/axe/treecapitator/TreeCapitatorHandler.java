package com.tricrotism.core.customitems.axe.treecapitator;

import com.tricrotism.core.utils.ItemUtils;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.meta.tags.ItemTagType;

import java.util.HashSet;
import java.util.Set;

public class TreeCapitatorHandler implements Listener {

    /**
     * Handles the TreeCapitator event, which is when a player breaks a log block with an axe, it'll break the entire tree.
     *
     * @param event
     */
    @EventHandler
    public void handleTreeCapitatior(BlockBreakEvent event) {
        if (ItemUtils.getCustomTag(event.getPlayer().getInventory().getItemInMainHand(), TreeCapitatorItem.NAMESPACE_KEY, ItemTagType.STRING).equals("true")) {
            handleTreeBreak(event.getBlock().getLocation());
        }
    }

    /**
     * Handles the breaking of a tree.
     *
     * @param startLocation the location of the block that was broken.
     */
    private void handleTreeBreak(Location startLocation) {
        Set<Block> visited = new HashSet<>();
        Set<Block> queue = new HashSet<>();

        queue.add(startLocation.getBlock());

        while (!queue.isEmpty()) {
            Block currentBlock = queue.iterator().next();
            queue.remove(currentBlock);
            visited.add(currentBlock);

            for (int xOffset = -1; xOffset <= 1; xOffset++) {
                for (int yOffset = -1; yOffset <= 1; yOffset++) {
                    for (int zOffset = -1; zOffset <= 1; zOffset++) {
                        Block adjacentBlock = currentBlock.getRelative(xOffset, yOffset, zOffset);
                        if (adjacentBlock.getType().toString().contains("LOG") || adjacentBlock.getType().toString().contains("LEAVES")) {
                            if (!visited.contains(adjacentBlock)) {
                                queue.add(adjacentBlock);
                                visited.add(adjacentBlock);
                                adjacentBlock.breakNaturally();
                            }
                        }
                    }
                }
            }
        }
    }

}
