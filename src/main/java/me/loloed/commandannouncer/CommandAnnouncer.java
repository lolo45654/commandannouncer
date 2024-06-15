package me.loloed.commandannouncer;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class CommandAnnouncer extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String command = event.getMessage();
        for (Player oPlayer : Bukkit.getOnlinePlayers()) {
            oPlayer.sendMessage(Component.text(player.getName() + " has issued: " + command));
        }
    }

    @EventHandler
    public void onCommand(ServerCommandEvent event) {
        String command = event.getCommand();
        for (Player oPlayer : Bukkit.getOnlinePlayers()) {
            oPlayer.sendMessage(Component.text("Console has issued: " + command));
        }
    }
}
