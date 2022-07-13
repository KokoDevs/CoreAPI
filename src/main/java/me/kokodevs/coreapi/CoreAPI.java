package me.kokodevs.coreapi;

import io.puharesource.mc.titlemanager.api.v2.TitleManagerAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public final class CoreAPI extends JavaPlugin {

    public static TitleManagerAPI titleManager;
    private static CoreAPI plugin;

    @Override
    public void onEnable() {

        plugin = this;

        titleManager = (TitleManagerAPI) Bukkit.getServer().getPluginManager().getPlugin("TitleManager");


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static CoreAPI getPlugin(){
        return plugin;
    }

}
