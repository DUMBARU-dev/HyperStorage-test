package com.github.dumbarudev.storage;

import net.kyori.adventure.text.Component;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class StorageExecutor {
    private HyperStoragePlugin plugin;
    public StorageExecutor(HyperStoragePlugin plugin){
        this.plugin=plugin;
    }
    public void executestoragecommand(Player player){
        if (!plugin.getCache().doplayerexistinhashmap(player)){
            player.kick(Component.text("Please rejoin"));
        }else {
//            if (plugin.getStorageUI().openStorageMainUI(player)){
            if (plugin.getStorageMenu().openStorageMainUI(player)){
                plugin.getCache().addplayertostorageinventorylist(player);
            }
            player.playSound(player, Sound.BLOCK_LEVER_CLICK,1f,1f);
        }
    }
}
