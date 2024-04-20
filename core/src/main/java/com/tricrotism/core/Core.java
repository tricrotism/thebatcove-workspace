package com.tricrotism.core;

import com.tricrotism.core.commands.ActionBarCommand;
import com.tricrotism.core.commands.BossBarCommand;
import com.tricrotism.core.commands.teleporting.TeleportCommand;
import com.tricrotism.core.customitems.axe.thorsaxe.GiveThorsAxeCommand;
import com.tricrotism.core.customitems.axe.thorsaxe.ThorsAxeHandler;
import com.tricrotism.core.customitems.axe.treecapitator.GiveTreecapitatorCommand;
import com.tricrotism.core.customitems.axe.treecapitator.TreeCapitatorHandler;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public final class Core extends JavaPlugin {

    private static FileConfiguration messagesConfig;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        messagesConfig = getConfig();

        this.getCommand("dev/actionbar").setExecutor(new ActionBarCommand());
        this.getCommand("dev/bossbar").setExecutor(new BossBarCommand());
        this.getCommand("teleport").setExecutor(new TeleportCommand());
        this.getCommand("givetreecapitator").setExecutor(new GiveTreecapitatorCommand());
        this.getServer().getPluginManager().registerEvents(new TreeCapitatorHandler(), this);
        this.getCommand("givethorsaxe").setExecutor(new GiveThorsAxeCommand());
        this.getServer().getPluginManager().registerEvents(new ThorsAxeHandler(), this);

        for (String key : messagesConfig.getKeys(true)) {
            getLogger().info(key);
        }
    }

    @Override
    public void onDisable() {

    }

    /**
     * Get a message from the messages.yml file.
     * @param key
     * @return
     */
    public static String getMessage(String key) {
        return messagesConfig.getString(key, "Message not found for key: " + key);
    }

    /**
     * Send a message to a player.
     * @param playerName
     * @param messageKey
     */
    public static void sendMessage(String playerName, String messageKey) {
        Player player = Bukkit.getPlayer(playerName);
        if (player != null && player.isOnline()) {
            player.sendMessage(getMessage(messageKey));
        }
    }

    /**
     * Send a message to a player with placeholders.
     * @param playerName
     * @param messageKey
     * @param placeholders
     */
    public static void sendMessage(String playerName, String messageKey, Map<String, Object> placeholders) {
        Player player = Bukkit.getPlayer(playerName);
        if (player != null && player.isOnline()) {
            String message = getMessage(messageKey);
            for (Map.Entry<String, Object> entry : placeholders.entrySet()) {
                message = message.replace("{" + entry.getKey() + "}", entry.getValue().toString());
            }
            player.sendMessage(message);
        }
    }
}
