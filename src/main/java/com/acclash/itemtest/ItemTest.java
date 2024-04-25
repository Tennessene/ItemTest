package com.acclash.itemtest;

import com.acclash.itemtest.commands.GiveItem;
import com.acclash.itemtest.listeners.RecipeListener;
import com.acclash.itemtest.listeners.ThrowListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class ItemTest extends JavaPlugin {

    static ItemTest plugin;

    public static ItemTest getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        getCommand("giveitem").setExecutor(new GiveItem());
        getServer().getPluginManager().registerEvents(new RecipeListener(), this);
        getServer().getPluginManager().registerEvents(new ThrowListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
