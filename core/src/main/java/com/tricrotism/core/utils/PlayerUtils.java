package com.tricrotism.core.utils;

import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;

public class PlayerUtils {

    /**
     * This method is used to send an action bar to a player.
     * @param player The player to send the action bar to.
     * @param msg The message of the action bar.
     * @since 0.0.0
     */

    public static void actionBar(Player player, String msg) {
        player.sendActionBar(parseMiniMessageMessage(msg));
    }

    /**
     * This method is used to send a title to a player.
     * @param player The player to send the title to.
     * @param title The title of the title.
     * @param subtitle The subtitle of the title.
     * @since 0.0.0
     */

    public static void title(Player player, String title, String subtitle) {
        Title mmTitle = Title.title(parseMiniMessageMessage(title), parseMiniMessageMessage(subtitle));

        player.showTitle(mmTitle);
    }

    /**
     * This method is used to send a boss bar to a player.
     * @param player The player to send the boss bar to.
     * @param msg The message of the boss bar.
     * @since 0.0.0
     */
    public static void bossBar(Player player, String msg) {
        player.showBossBar(BossBar.bossBar(parseMiniMessageMessage(msg), 1.0f, BossBar.Color.BLUE, BossBar.Overlay.PROGRESS));
    }

    /**
     * This method is used to parse a message using MiniMessage.
     * @param msg The message to parse.
     * @return The parsed message.
     * @since 0.0.0
     */

    private static Component parseMiniMessageMessage(String msg) {
        MiniMessage mm = MiniMessage.miniMessage();

        return mm.deserialize(msg);
    }

}
