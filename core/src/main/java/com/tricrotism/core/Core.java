package com.tricrotism.core;

import com.tricrotism.core.commands.ActionBarCommand;
import com.tricrotism.core.commands.BossBarCommand;
import com.tricrotism.core.commands.teleporting.TeleportCommand;
import com.tricrotism.core.commands.teleporting.TpaAcceptCommand;
import com.tricrotism.core.commands.teleporting.TpaCommand;
import com.tricrotism.core.commands.teleporting.TpaDenyCommand;
import com.tricrotism.core.customitems.axe.thorsaxe.GiveThorsAxeCommand;
import com.tricrotism.core.customitems.axe.thorsaxe.ThorsAxeHandler;
import com.tricrotism.core.customitems.axe.treecapitator.GiveTreecapitatorCommand;
import com.tricrotism.core.customitems.axe.treecapitator.TreeCapitatorHandler;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public final class Core extends JavaPlugin {

    public static FileConfiguration messagesConfig;
    public static Core plugin;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        messagesConfig = getConfig();
        plugin = this;

        initCommandAndEvents();
    }

    @Override
    public void onDisable() {

    }

    /**
     * Command/Event Initializer
     *
     * @since 0.0.1
     */
    private void initCommandAndEvents() {
        registerCommand("dev/actionbar", ActionBarCommand.class);
        registerCommand("dev/bossbar", BossBarCommand.class);
        registerCommand("teleport", TeleportCommand.class);
        registerCommand("givetreecapitator", GiveTreecapitatorCommand.class);
        registerEvent(TreeCapitatorHandler.class);
        registerCommand("givethorsaxe", GiveThorsAxeCommand.class);
        registerEvent(ThorsAxeHandler.class);
        registerCommand("tpa", TpaCommand.class);
        registerCommand("tpaccept", TpaAcceptCommand.class);
        registerCommand("tpdeny", TpaDenyCommand.class);
    }


    /**
     * This method is used to have a simpler way to register a command
     *
     * @param commandName The name of the command
     * @param clazz       The class that will handle the command
     * @since 0.0.1
     */
    public void registerCommand(String commandName, Class<?> clazz) {
        try {
            Bukkit.getPluginCommand(commandName).setExecutor((org.bukkit.command.TabExecutor) clazz.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to have a simpler way to register an event
     *
     * @param clazz The class that will handle the event
     * @since 0.0.1
     */
    public void registerEvent(Class<?> clazz) {
        try {
            Bukkit.getPluginManager().registerEvents((org.bukkit.event.Listener) clazz.newInstance(), this);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
