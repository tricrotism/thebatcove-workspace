package com.tricrotism.core.utils;

import com.tricrotism.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Map;

/**
 * Utility class for handling messages and localization.
 */
public class LocaleUtils {

    /**
     * Get a message from the messages.yml file.
     * @param key
     * @return
     */
    public static String getMessage(String key) {
        return Core.messagesConfig.getString(key, "Message not found for key: " + key);
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
