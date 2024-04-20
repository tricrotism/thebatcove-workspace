package com.tricrotism.core.customitems.axe.treecapitator;

import com.tricrotism.core.utils.ItemUtils;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.tags.ItemTagType;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class TreeCapitatorHandler implements Listener {

    /**
     * Handles the TreeCapitator event, which is when a player breaks a log block with an axe, it'll break the entire tree.
     *
     * @param event
     */
   @EventHandler
    public void handleTreeCapitatior(BlockBreakEvent event) {
        Object customTag = ItemUtils.getCustomTag(event.getPlayer().getInventory().getItemInMainHand(), TreeCapitatorItem.NAMESPACE_KEY, ItemTagType.STRING);
        if (customTag != null && customTag.equals("true")) {
            handleTreeBreak(event.getBlock().getLocation(), event.getPlayer().getInventory().getItemInMainHand());
        }
    }

    /**
     * Handles the breaking of a tree.
     *
     * @param startLocation the location of the block that was broken.
     */
    private void handleTreeBreak(Location startLocation, ItemStack item) {
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

                                int durability = item.getDurability();
                                if (durability < item.getType().getMaxDurability()) {
                                    item.setDurability((short) (durability + 1));
                                } else {
                                    item.setAmount(0);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
