package me.kokodevs.coreapi.utils;

import org.bukkit.command.PluginCommand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;

public class LoaderUtils {

    public static void loadAllListeners(Plugin plugin, String listenersPackage) {
        String packageName = plugin.getClass().getPackage().getName();

        for (Class<?> clazz : new Reflections(packageName + "." + listenersPackage)
                .getSubTypesOf(Listener.class)
        ) {
            try {
                Listener listener = (Listener) clazz.getDeclaredConstructor().newInstance();
                plugin.getServer().getPluginManager().registerEvents(listener, plugin);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }
    public static void loadAllCommands(Plugin plugin, String commandsPackage) {
        String packageName = plugin.getClass().getPackage().getName();

        for (Class<?> clazz : new Reflections(packageName + "." + commandsPackage)
                .getSubTypesOf(CommandUtils.class)
        ) {
            try {
                CommandUtils pluginCommand = (CommandUtils) clazz.getDeclaredConstructor().newInstance();
                JavaPlugin javaPlugin = JavaPlugin.getProvidingPlugin(plugin.getClass());
                javaPlugin.getCommand(pluginCommand.getCommandInfo().name()).setExecutor(pluginCommand);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

}
