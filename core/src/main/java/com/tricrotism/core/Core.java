package com.tricrotism.core;

import com.tricrotism.core.commands.ActionBarCommand;
import com.tricrotism.core.commands.BossBarCommand;
import com.tricrotism.core.customitems.axe.treecapitator.GiveTreecapitatorCommand;
import com.tricrotism.core.customitems.axe.treecapitator.TreeCapitatorHandler;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("dev/actionbar").setExecutor(new ActionBarCommand());
        this.getCommand("dev/bossbar").setExecutor(new BossBarCommand());
        this.getCommand("givetreecapitator").setExecutor(new GiveTreecapitatorCommand());

        this.getServer().getPluginManager().registerEvents(new TreeCapitatorHandler(), this);
    }

    @Override
    public void onDisable() {

    }
}
