package com.tricrotism.core.customitems.axe.thorsaxe;

import com.tricrotism.core.Core;
import com.tricrotism.core.utils.ItemUtils;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.tags.ItemTagType;

import java.util.HashMap;
import java.util.Map;

public class ThorsAxeHandler implements Listener {

    /**
     * Hashmap to store the cooldown of the Thor's Axe for the player.
     * Key: player
     * Value: cooldown
     *
     * @since 0.0.0
     */
    private static HashMap<Player, Long> cooldown = new HashMap<>();

    /**
     * Handles the Thor's Axe event, which is when a player right clicks (with a minute cooldown) it'll strike lightning.
     *
     * @param event
     * @since 0.0.0
     */
    @EventHandler
    public void onThorsAxeRightClick(PlayerInteractEvent event) {
        Object customTag = ItemUtils.getCustomTag(event.getPlayer().getInventory().getItemInMainHand(), ThorsAxeItem.NAMESPACE_KEY, ItemTagType.STRING);
        if (customTag != null && customTag.equals("true")) {
            if (event.getAction().isRightClick()) {
                if (event.getClickedBlock() != null && event.getClickedBlock().getType().toString().contains("LOG")) {
                    event.setCancelled(true);
                    return;
                }
                handleLightningStrike(event.getPlayer());
            } else {
                event.setCancelled(true);
            }
        }
    }

    /**
     * Handles the lightning strike.
     *
     * @param player gets the player, to obtain the eye location of the player.
     * @since 0.0.0
     */
    private void handleLightningStrike(Player player) {
        if (!checkCooldown(player)) {
            player.getWorld().strikeLightning(player.getTargetBlock(600).getLocation());
        } else {
            Core.sendMessage(player.getName(), "core.customItems.thorsAxe.cooldown", Map.of(
                    "time", getCooldownDuration(player)
            ));
//            player.sendMessage("§cYou must wait " + getCooldownDuration(player) + " seconds before using this again.");
        }
    }

    /**
     * Checks if the cooldown is over using a Scheduler.
     *
     * @param player gets the player, to check if the player is in the cooldown hashmap.
     * @return checks the cooldown of the player.
     * @since 0.0.0
     */
    private boolean checkCooldown(Player player) {
        if (cooldown.containsKey(player)) {
            if (System.currentTimeMillis() - cooldown.get(player) < 60000) {
                return true;
            }
        }
        cooldown.put(player, System.currentTimeMillis());
        return false;
    }

    /**
     * Checks the duration of the cooldown
     *
     * @param player gets the player, to check if the player is in the cooldown hashmap.
     * @return duration of the cooldown in seconds
     * @since 0.0.0
     */
    private long getCooldownDuration(Player player) {
        return (60000 - (System.currentTimeMillis() - cooldown.get(player))) / 1000;
    }
}
