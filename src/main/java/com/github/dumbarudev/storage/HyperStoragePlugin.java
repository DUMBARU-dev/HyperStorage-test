package com.github.dumbarudev.storage;

import com.github.dumbarudev.storage.Data.Cache;
import com.github.dumbarudev.storage.Data.FileHandler;
import com.github.dumbarudev.storage.Data.PlayerDataHandler;
import com.github.dumbarudev.storage.Data.Objects.Storage;
import com.github.dumbarudev.storage.Listeners.ListenersManager;
import com.github.dumbarudev.storage.UI.EnderChestUI;
import com.github.dumbarudev.storage.UI.StorageUI;
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
    private StorageUI storageUI;
    private ListenersManager listenersManager;
    private EnderChestUI enderChestUI;
    @Override
    public void onEnable() {
        preregisterServices();
        fileHandler=new FileHandler(this);
        cache = new Cache(this);
        playerDataHandler = new PlayerDataHandler(this);
        storageExecutor=new StorageExecutor(this);
        storageUI=new StorageUI(this);
        listenersManager= new ListenersManager(this);
        enderChestUI=new EnderChestUI(this);


        File file = getDataFolder();
        if (!file.exists()){
            file.mkdirs();
        }

        getServer().getPluginCommand("storage").setExecutor(new Commands(this));
        getCommand("adminstorage").setExecutor(new Commands(this));
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

    public StorageUI getStorageUI() {
        return storageUI;
    }

    public EnderChestUI getEnderChestUI() {
        return enderChestUI;
    }

    public ListenersManager getListenersManager() {
        return listenersManager;
    }
}
