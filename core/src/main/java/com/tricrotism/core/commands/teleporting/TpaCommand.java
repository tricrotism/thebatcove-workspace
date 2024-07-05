package com.tricrotism.core.commands.teleporting;

import com.tricrotism.core.Core;
import com.tricrotism.core.utils.LocaleUtils;
import com.tricrotism.core.utils.TeleportUtils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TpaCommand implements TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(commandSender instanceof Player)) {
            LocaleUtils.sendMessage(commandSender.getName(), "generic.mustBeAPlayer");
            return false;
        }

        Player player = (Player) commandSender;
        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            LocaleUtils.sendMessage(player.getName(), "generic.playerNotFound");
            return false;
        }

        if (player == target) {
            LocaleUtils.sendMessage(player.getName(), "core.commands.tpa.cannotTeleportToSelf");
            return false;
        }

        if (TeleportUtils.pendingTeleports.containsKey(target)) {
            LocaleUtils.sendMessage(player.getName(), "core.commands.tpa.alreadyHaveAPendingRequest");
            return false;
        }

        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.runTaskLater(Core.plugin, () -> {
            if (TeleportUtils.pendingTeleports.containsKey(target)) {
                TeleportUtils.pendingTeleports.remove(target);
                LocaleUtils.sendMessage(player.getName(), "core.commands.tpa.requestExpired");
                LocaleUtils.sendMessage(target.getName(), "core.commands.tpa.requestExpired");
            }
        }, 1200L);

        Map<Player, TeleportUtils> data = TeleportUtils.pendingTeleports.computeIfAbsent(target, k -> new HashMap<Player, TeleportUtils>());
        data.put(player, TeleportUtils.TPA);

        LocaleUtils.sendMessage(player.getName(), "core.commands.tpa.requestSent", Map.of(
                "player", target.getName()
        ));

        LocaleUtils.sendMessage(target.getName(), "core.commands.tpa.requestReceived", Map.of(
                "player", player.getName()
        ));

        TextComponent base = new TextComponent();
        TextComponent accept = new TextComponent("§a[Accept]");
        accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpaccept"));
        TextComponent deny = new TextComponent("§c[Deny]");
        deny.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpdeny"));

        base.addExtra(accept);
        base.addExtra(" or ");
        base.addExtra(deny);
        target.sendMessage(base);
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return List.of();
    }
}
