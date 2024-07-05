package com.tricrotism.core.commands;

import com.tricrotism.core.utils.LocaleUtils;
import com.tricrotism.core.utils.PlayerUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * This class is used to handle the dev/actionbar command. Which sends an action bar to the sender.
 * @since 0.0.0
 * @version 0.0.1
 */

public class ActionBarCommand implements TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(command.getName().equalsIgnoreCase("dev/actionbar")) {
            if(args.length == 0) {
                LocaleUtils.sendMessage(commandSender.getName(), "core.commands.actionbar.usage");
                return false;
            }

            Player player = (Player) commandSender;

            String message = String.join(" ", args);
            PlayerUtils.actionBar(player, message);
            LocaleUtils.sendMessage(player.getName(), "core.commands.actionbar.sent");

            return true;
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        return null;
    }
}
