package me.kokodevs.kokoapi;

import io.puharesource.mc.titlemanager.api.v2.TitleManagerAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public final class KokoAPI extends JavaPlugin {

    public static TitleManagerAPI titleManager;
    private static KokoAPI plugin;

    @Override
    public void onEnable() {

        plugin = this;

        titleManager = (TitleManagerAPI) Bukkit.getServer().getPluginManager().getPlugin("TitleManager");


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static KokoAPI getPlugin(){
        return plugin;
    }

}
