package com.tricrotism.core.customitems.axe.thorsaxe;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GiveThorsAxeCommand implements TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(command.getName().equalsIgnoreCase("givethorsaxe")) {
            if(args.length == 0) {
                commandSender.sendMessage("You need to specify a player!");
                return false;
            }

            String playerName = args[0];
            Player player = Bukkit.getPlayer(playerName);

            if(player == null) {
                commandSender.sendMessage("Player not found!");
                return false;
            }


            ThorsAxeItem.createItem(player);
            player.sendMessage("You have been given a Thor's Axe!");

            return true;
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }

}
