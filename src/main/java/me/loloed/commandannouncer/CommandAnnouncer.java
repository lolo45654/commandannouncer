package me.loloed.commandannouncer;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class CommandAnnouncer extends JavaPlugin implements Listener {
    public static final String[] BLACKLISTED = new String[] { "msg", "w", "tell", "home", "tpa", "tpahere", "team", "sethome", "minecraft:msg", "minecraft:w", "minecraft:tell" };

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginCommand("announcerworking").setExecutor(this);
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (!player.isOp()) return;
        String command = event.getMessage();
        for (String s : BLACKLISTED) {
            if (command.startsWith("/" + s)) return;
        }
        for (Player oPlayer : Bukkit.getOnlinePlayers()) {
            oPlayer.sendMessage(Component.text(player.getName() + " has issued: " + command));
        }
    }

    @EventHandler
    public void onCommand(ServerCommandEvent event) {
        String command = event.getCommand();
        for (String s : BLACKLISTED) {
            if (command.startsWith(s)) return;
        }
        for (Player oPlayer : Bukkit.getOnlinePlayers()) {
            oPlayer.sendMessage(Component.text("Console has issued: " + command));
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        sender.sendMessage(Component.text("Yes, I am active."));
        return true;
    }
}
