package com.github.dumbarudev.storage.Data;

import com.github.dumbarudev.storage.HyperStorage.core.Storage;
import com.github.dumbarudev.storage.HyperStoragePlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;

public class PlayerDataHandler {
    private HyperStoragePlugin plugin;
    private Cache cache;
    public PlayerDataHandler(HyperStoragePlugin plugin){
        this.plugin=plugin;
        cache=plugin.getCache();
        scheduledplayerdatatimer();
    }
    public Storage loadPlayerData(Player player){
      return Json_utils.loadfie(new File(cache.getDatafolder(),player.getUniqueId().toString()+".bin"));
    }

    public void scheduledplayerdatatimer(){
        Bukkit.getScheduler().runTaskTimer(plugin,()->{
            for (Player player:Bukkit.getOnlinePlayers()){
                savePlayerData(player);
            }
        },20,6000);
    }

    public void savePlayerData(Player player){
        if (!cache.doplayerexistinhashmap(player)){
            player.kick();
            return;
        }
        File playerfile = new File(cache.getDatafolder(),player.getUniqueId().toString()+".bin");
        Storage storage = cache.getPlayerStoragedatafromstorageMap(player);
        Json_utils.savefile(plugin,playerfile,storage);
    }
}
