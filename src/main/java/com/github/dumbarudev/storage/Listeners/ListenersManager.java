package com.github.dumbarudev.storage.Listeners;

import com.github.dumbarudev.storage.HyperStoragePlugin;
import org.bukkit.Bukkit;

public class ListenersManager {
    private HyperStoragePlugin plugin;
    public ListenersManager(HyperStoragePlugin plugin){
        this.plugin=plugin;
        RegisterEventsClasses();
    }
    private void RegisterEventsClasses(){
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(plugin),plugin);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(plugin),plugin);
        Bukkit.getPluginManager().registerEvents(new InventoryListener(plugin),plugin);
    }
}
