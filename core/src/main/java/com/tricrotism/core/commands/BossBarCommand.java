package com.tricrotism.core.commands;

import com.tricrotism.core.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * This class is used to handle the dev/bossbar command. Which sends a bossbar to the sender/all online players.
 * @since 0.0.0
 */

public class BossBarCommand  implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(command.getName().equalsIgnoreCase("dev/bossbar")) {
            if(args.length == 0) {
                commandSender.sendMessage("You need to specify a player/all");
                return false;
            }

            if(args.length == 1) {
                commandSender.sendMessage("You need to specify a message!");
                return false;
            }

            String message = String.join(" ", args).replace(args[0] + " ", "");

            if(args[0].equalsIgnoreCase("all")) {
                for(Player player : Bukkit.getOnlinePlayers()) {
                    PlayerUtils.bossBar(player, message);
                }
            } else {
                Player player = Bukkit.getPlayer(args[0]);
                if(player == null) {
                    commandSender.sendMessage("Player not found!");
                    return false;
                }
                PlayerUtils.bossBar(player, message);
            }

            return true;
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}
