package com.tricrotism.core.customitems.axe.treecapitator;

import com.tricrotism.core.utils.LocaleUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * This class is used to handle the givetreecapitator command. Which gives a player a Tree Capitator.
 * @since 0.0.0
 */
public class GiveTreecapitatorCommand  implements TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(command.getName().equalsIgnoreCase("givetreecapitator")) {
            if(args.length == 0) {
                LocaleUtils.sendMessage(commandSender.getName(), "generic.specifyAPlayer");
                return false;
            }

            String playerName = args[0];
            Player player = Bukkit.getPlayer(playerName);

            if(player == null) {
                LocaleUtils.sendMessage(commandSender.getName(), "generic.playerNotFound");
                return false;
            }


            TreeCapitatorItem.createItem(player);
            LocaleUtils.sendMessage(playerName, "core.customItems.treeCapitator.givenAxe");

            return true;
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}
