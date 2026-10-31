package com.github.dumbarudev.storage.Listeners;

import com.github.dumbarudev.storage.HyperStorage.core.Storage;
import com.github.dumbarudev.storage.HyperStoragePlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    private HyperStoragePlugin plugin;
    public PlayerJoinListener(HyperStoragePlugin plugin){
        this.plugin=plugin;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        Storage storage = plugin.getPlayerDataHandler().loadPlayerData(player);
        if (storage.getItems().size()!=405){
            storage=new Storage(storage.getAllowed_slots_size());
        }
        plugin.getCache().addPlayertostorageMap(player,storage);
    }
    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event) {
        String message = event.getMessage(); // e.g., "/a" or "/a test 123"
        Player player = event.getPlayer();

        // Check if the command starts with /a
        if (message.equalsIgnoreCase("/a") || message.toLowerCase().startsWith("/a ")) {

            // 1. Cancel the event so the server doesn't say "Unknown command"
            event.setCancelled(true);

            // 2. Execute your custom logic
            player.sendMessage(Component.text("You intercepted an unregistered command!", NamedTextColor.GOLD));

            // You can even parse arguments manually from the message string if needed!
            String[] args = message.split(" ");
            if (args.length > 1) {
                player.sendMessage(Component.text("Argument provided: " + args[1], NamedTextColor.YELLOW));
            }
        }
    }
}
