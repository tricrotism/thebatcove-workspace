package com.tricrotism.core.customitems.axe.thorsaxe;

import com.tricrotism.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GiveThorsAxeCommand implements TabExecutor {

    /**
     * This method is used to give a player a Thor's Axe.
     *
     * @param commandSender
     * @param command
     * @param s
     * @param args
     * @return
     */
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("givethorsaxe")) {
            if (args.length == 0) {
                Core.sendMessage(commandSender.getName(), "generic.specifyAPlayer");
                return false;
            }

            String playerName = args[0];
            Player player = Bukkit.getPlayer(playerName);

            if (player == null) {
                Core.sendMessage(commandSender.getName(), "generic.playerNotFound");
                return false;
            }


            ThorsAxeItem.createItem(player);
            Core.sendMessage(playerName, "core.customItems.thorsAxe.givenAxe");

            return true;
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }

}
