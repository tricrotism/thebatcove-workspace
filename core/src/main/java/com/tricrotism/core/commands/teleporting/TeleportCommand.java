package com.tricrotism.core.commands.teleporting;

import com.tricrotism.core.utils.LocaleUtils;
import com.tricrotism.core.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

/**
 * TeleportCommand is a command that allows a player to teleport force teleport to other player.
 * @since 0.0.1
 * @version 0.0.1
 */
public class TeleportCommand  implements TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(commandSender instanceof Player)) {
            LocaleUtils.sendMessage(commandSender.getName(), "generic.mustBeAPlayer");
            return false;
        }

        Player player = (Player) commandSender;

        switch (args.length) {
            case 1:
                Player target = PlayerUtils.findPlayer(player.getServer().getPlayer(args[0]).getName());
                if (target == null) {
                    LocaleUtils.sendMessage(player.getName(), "generic.playerNotFound");
                    return false;
                }
                player.teleport(target);
                LocaleUtils.sendMessage(player.getName(), "core.commands.teleport.teleport1", Map.of(
                        "player", target.getName()
                ));
                break;
            case 3:
                double x = Double.parseDouble(args[0]);
                double y = Double.parseDouble(args[1]);
                double z = Double.parseDouble(args[2]);
                player.teleport(player.getWorld().getBlockAt((int) x, (int) y, (int) z).getLocation());
                LocaleUtils.sendMessage(player.getName(), "core.commands.teleport.teleport2", Map.of(
                        "x", x,
                        "y", y,
                        "z", z
                ));
                break;
            case 4:
                double x1 = Double.parseDouble(args[0]);
                double y1 = Double.parseDouble(args[1]);
                double z1 = Double.parseDouble(args[2]);
                var world1 = Bukkit.getServer().getWorld(args[3]);

                if (world1 == null) {
                    LocaleUtils.sendMessage(player.getName(), "core.commands.teleport.invalidWorld");
                    return false;
                }

                player.teleport(world1.getBlockAt((int) x1, (int) y1, (int) z1).getLocation());
                LocaleUtils.sendMessage(player.getName(), "core.commands.teleport.teleport3", Map.of(
                        "x", x1,
                        "y", y1,
                        "z", z1,
                        "world", world1
                ));
                break;
            case 5:
                double x2 = Double.parseDouble(args[0]);
                double y2 = Double.parseDouble(args[1]);
                double z2 = Double.parseDouble(args[2]);
                var world2 = Bukkit.getServer().getWorld(args[3]);

                if (world2 == null) {
                    LocaleUtils.sendMessage(player.getName(), "core.commands.teleport.invalidWorld");
                    return false;
                }

                Player target2 = PlayerUtils.findPlayer(player.getServer().getPlayer(args[4]).toString());
                if (target2 == null) {
                    player.sendMessage("Player not found!");
                    return false;
                }
                target2.teleport(world2.getBlockAt((int) x2, (int) y2, (int) z2).getLocation());
                LocaleUtils.sendMessage(player.getName(), "core.commands.teleport.teleport4", Map.of(
                        "target", target2.getName(),
                        "x", x2,
                        "y", y2,
                        "z", z2,
                        "world", world2
                ));
                break;
            default:
                player.sendMessage("Usage: /teleport <player>");
                player.sendMessage("Usage: /teleport <x> <y> <z>");
                player.sendMessage("Usage: /teleport <x> <y> <z> <world>");
                return false;
        }
        return true;
    }


    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}
