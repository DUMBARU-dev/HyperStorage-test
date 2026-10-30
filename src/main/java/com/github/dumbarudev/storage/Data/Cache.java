package com.github.dumbarudev.storage.Data;

import com.github.dumbarudev.storage.Data.Objects.Storage;
import com.github.dumbarudev.storage.HyperStoragePlugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cache {
    private HyperStoragePlugin plugin;
    private FileHandler fileHandler;
    private File pluginfolder;
    private File datafolder;

    //Player Cache
    private HashMap<Player, Storage> playerStorageHashMap = new HashMap<>();
    private List<Player> playeroninventorystorage = new ArrayList<>();
    private List<Player> enderchestinventory = new ArrayList<>();


    public Cache(HyperStoragePlugin plugin){
        //Instances
        this.plugin=plugin;
        fileHandler=plugin.getFileHandler();
        //setup folders
        setupDirectories();


    }
    private void setupDirectories(){
        pluginfolder=plugin.getDataFolder();
        datafolder=new File(pluginfolder,"data");
        fileHandler.checkOrCreateDirectory(datafolder);
    }

    public HyperStoragePlugin getPlugin() {
        return plugin;
    }

    public File getDatafolder() {
        return datafolder;
    }

    public void addPlayertostorageMap(Player player,Storage storage){
        playerStorageHashMap.put(player,storage);
    }
    public Storage getPlayerStoragedatafromstorageMap(Player player){
        return playerStorageHashMap.get(player);
    }
    public void removePlayerfromstorageMap(Player player){playerStorageHashMap.remove(player);}
    public boolean doplayerexistinhashmap(Player player){
        return playerStorageHashMap.containsKey(player);
    }

    public void addplayertostorageinventorylist(Player player) {
        if (!playeroninventorystorage.contains(playeroninventorystorage.add(player)))
            playeroninventorystorage.add(player);
    }
    public void remoneplayertostorageinventorylist(Player player) {
        if (playeroninventorystorage.contains(playeroninventorystorage.add(player)))
            playeroninventorystorage.remove(player);
    }

    public boolean doplayerexistinstorageinventory(Player player){
        return playeroninventorystorage.contains(player);
    }

}
