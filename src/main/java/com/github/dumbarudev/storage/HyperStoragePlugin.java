package com.github.dumbarudev.storage;

import com.github.dumbarudev.storage.Data.Cache;
import com.github.dumbarudev.storage.Data.FileHandler;
import com.github.dumbarudev.storage.Data.PlayerDataHandler;
import com.github.dumbarudev.storage.HyperStorage.core.Storage;
import com.github.dumbarudev.storage.HyperStorage.api.EnderChestMenu;
import com.github.dumbarudev.storage.HyperStorage.api.StorageMenu;
import com.github.dumbarudev.storage.Listeners.ListenersManager;
import com.github.dumbarudev.storage.HyperStorage.core.UI.EnderChestUI;
import com.github.dumbarudev.storage.HyperStorage.core.UI.StorageUI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class HyperStoragePlugin extends JavaPlugin {
    private Cache cache;
    private FileHandler fileHandler;
    private PlayerDataHandler playerDataHandler;
    private StorageExecutor storageExecutor;
    private ListenersManager listenersManager;
    private EnderChestMenu enderChestMenu;
    private StorageMenu storageMenu;
    @Override
    public void onEnable() {
        preregisterServices();
        fileHandler=new FileHandler(this);
        cache = new Cache(this);
        playerDataHandler = new PlayerDataHandler(this);
        storageExecutor=new StorageExecutor(this);
        enderChestMenu=new EnderChestUI(this);
        listenersManager= new ListenersManager(this);
        storageMenu = new StorageUI(this);


        File file = getDataFolder();
        if (!file.exists()){
            file.mkdirs();
        }

        getServer().getPluginCommand("storage").setExecutor(new Commands(this));
        getCommand("adminstorage").setExecutor(new Commands(this));
        getCommand("backpack").setExecutor(new Commands(this));
        getCommand("enderchest").setExecutor(new Commands(this));
        cacheplayers();
        Bukkit.getLogger().info(Component.text("HyperStoragePlugin enabled").color(NamedTextColor.BLUE).toString());

    }

    private void cacheplayers(){
        for (Player player:Bukkit.getOnlinePlayers()){
            Storage storage = playerDataHandler.loadPlayerData(player);
            cache.addPlayertostorageMap(player,storage);
        }
    }

    private void preregisterServices(){
        ServiceRegistry.register(HyperStoragePlugin.class,this);
    }

    public FileHandler getFileHandler() {
        return fileHandler;
    }

    public PlayerDataHandler getPlayerDataHandler() {
        return playerDataHandler;
    }

    public Cache getCache() {
        return cache;
    }

    public StorageExecutor getStorageExecutor() {
        return storageExecutor;
    }

    public EnderChestMenu getEnderChestMenu() {
        return enderChestMenu;
    }

    public ListenersManager getListenersManager() {
        return listenersManager;
    }

    public StorageMenu getStorageMenu() {return storageMenu;}
}
