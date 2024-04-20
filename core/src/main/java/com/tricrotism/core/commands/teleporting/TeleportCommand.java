package com.tricrotism.core.commands.teleporting;

import com.tricrotism.core.Core;
import com.tricrotism.core.utils.PlayerUtils;
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
 */
public class TeleportCommand  implements TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(commandSender instanceof Player)) {
            Core.sendMessage(commandSender.getName(), "generic.mustBeAPlayer");
            return false;
        }

        Player player = (Player) commandSender;

        switch (args.length) {
            case 1:
                Player target = PlayerUtils.findPlayer(player.getServer().getPlayer(args[0]).toString());
                if (target == null) {
                    Core.sendMessage(player.getName(), "generic.playerNotFound");
                    return false;
                }
                player.teleport(target);
                Core.sendMessage(player.getName(), "core.commands.teleport.teleport1", Map.of(
                        "player", target.getName()
                ));
                break;
            case 3:
                double x = Double.parseDouble(args[0]);
                double y = Double.parseDouble(args[1]);
                double z = Double.parseDouble(args[2]);
                player.teleport(player.getWorld().getBlockAt((int) x, (int) y, (int) z).getLocation());
                Core.sendMessage(player.getName(), "core.commands.teleport.teleport2", Map.of(
                        "x", x,
                        "y", y,
                        "z", z
                ));
                break;
            case 4:
                double x1 = Double.parseDouble(args[0]);
                double y1 = Double.parseDouble(args[1]);
                double z1 = Double.parseDouble(args[2]);
                String world1 = args[3];
                player.teleport(player.getServer().getWorld(world1).getBlockAt((int) x1, (int) y1, (int) z1).getLocation());
                Core.sendMessage(player.getName(), "core.commands.teleport.teleport3", Map.of(
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
                String world2 = args[3];
                Player target2 = PlayerUtils.findPlayer(player.getServer().getPlayer(args[4]).toString());
                if (target2 == null) {
                    player.sendMessage("Player not found!");
                    return false;
                }
                target2.teleport(player.getServer().getWorld(world2).getBlockAt((int) x2, (int) y2, (int) z2).getLocation());
                Core.sendMessage(player.getName(), "core.commands.teleport.teleport4", Map.of(
                        "target", target2.getName(),
                        "x", x2,
                        "y", y2,
                        "z", z2,
                        "world", world2
                ));
                break;
            default:
                player.sendMessage("Usage: /teleport <player>");
                return false;
        }
        return true;
    }


    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}
