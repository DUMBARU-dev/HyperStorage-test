package com.github.dumbarudev.storage.Listeners;

import com.github.dumbarudev.storage.Data.Cache;
import com.github.dumbarudev.storage.HyperStoragePlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    private HyperStoragePlugin plugin;
    private Cache cache;
    public PlayerQuitListener(HyperStoragePlugin plugin){
        this.plugin=plugin;
        cache=plugin.getCache();
    }
    @EventHandler
    public void onleave(PlayerQuitEvent event){
        Player player = event.getPlayer();
        cache.remoneplayertostorageinventorylist(player);
        cache.removePlayerfromstorageMap(player);

    }

}
