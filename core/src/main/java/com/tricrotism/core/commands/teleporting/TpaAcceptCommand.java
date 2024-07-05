package com.tricrotism.core.commands.teleporting;

import com.tricrotism.core.utils.LocaleUtils;
import com.tricrotism.core.utils.TeleportUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

import static com.tricrotism.core.utils.TeleportUtils.pendingTeleports;

public class TpaAcceptCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player sender = (Player) commandSender;
        
        if (!pendingTeleports.containsKey(sender)) {
            LocaleUtils.sendMessage(sender.getName(), "core.commands.tpa.noPendingRequests");
            return false;
        }

        Map<Player, TeleportUtils> data = pendingTeleports.get(sender);
        if (data == null) {
            LocaleUtils.sendMessage(sender.getName(), "core.commands.tpa.noPendingRequests");
            return false;
        }

        Player target = data.keySet().iterator().next();
        if (target == null) {
            LocaleUtils.sendMessage(sender.getName(), "core.commands.tpa.noLongerOnline", Map.of(
                    "player", target.getName()
            ));
            return false;
        }

        TeleportUtils type = data.get(target);
        if (type == null) {
            LocaleUtils.sendMessage(sender.getName(), "core.commands.tpa.noLongerOnline", Map.of(
                    "player", target.getName()
            ));
            return false;
        }

        if (type == TeleportUtils.TPA) {
            target.teleport(sender.getLocation());
            LocaleUtils.sendMessage(sender.getName(), "core.commands.tpa.accept.selfToOther", Map.of(
                    "player", target.getName()
            ));
            LocaleUtils.sendMessage(target.getName(), "core.commands.tpa.tpAccepted");
        } else if (type == TeleportUtils.TPAHERE) {
            sender.teleport(target.getLocation());
            LocaleUtils.sendMessage(sender.getName(), "core.commands.tpa.accept.otherToSelf", Map.of(
                    "player", target.getName()
            ));
            LocaleUtils.sendMessage(target.getName(), "core.commands.tpa.tpAccepted");
        }

        pendingTeleports.remove(sender);
        pendingTeleports.remove(target);
        
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return List.of();
    }
}
