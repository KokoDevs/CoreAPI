package me.kokodevs.coreapi.utils;

import me.kokodevs.coreapi.interfaces.CommandInfo;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CommandUtils implements CommandExecutor {
    private final CommandInfo commandInfo;

    public CommandUtils() {
        this.commandInfo = getClass().getDeclaredAnnotation(CommandInfo.class);
        Objects.requireNonNull(commandInfo, "Commands must have CommandInfo annotations");
    }

    public CommandInfo getCommandInfo() {
        return commandInfo;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!commandInfo.permission().isEmpty()){
            if(!sender.hasPermission(commandInfo.permission())){
                sender.sendMessage("§cYou don't have permission to execute this command.");
                return true;
            }
        }

        if(commandInfo.requiresPlayer()){
            if(!(sender instanceof Player)){
                sender.sendMessage("§cYou must be a player to execute this command.");
                return true;
            }
            execute((Player) sender, args);
            return true;
        }

        execute(sender, args);
        return true;
    }

    public void execute(Player player, String[] args) {}
     public void execute(CommandSender sender, String[] args) {}
}
