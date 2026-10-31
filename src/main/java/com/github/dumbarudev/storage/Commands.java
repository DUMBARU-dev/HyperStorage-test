package com.github.dumbarudev.storage;

import com.github.dumbarudev.storage.Data.Cache;
import com.github.dumbarudev.storage.HyperStorage.core.UI.BACKPACK;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class Commands implements CommandExecutor {
    private HyperStoragePlugin plugin;
    private Cache cache;
    public Commands(HyperStoragePlugin plugin){
        this.plugin=plugin;
        this.cache=plugin.getCache();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (sender instanceof Player player){
            if (command.getName().equalsIgnoreCase("storage")){
                if(!player.isOp()||!player.hasPermission("hyperstorage.admin")){
                    plugin.getStorageExecutor().executestoragecommand(player);
                }else {
                    if (args.length==0){
                        plugin.getStorageExecutor().executestoragecommand(player);
                    }else if (args.length>0){
                        if (args[0].equalsIgnoreCase("help")){
                            player.sendMessage("---------------- [HyperStorage] ----------------");
                            player.sendMessage("- /storage help");
                            player.sendMessage("- /storage setslots <player> <slots 0-405>");
                        } else if (args[0].equalsIgnoreCase("setlevel")) {
                            executesetlevel(args[1],args[2],player);
                        }
                    }
                }
            }
            else if (command.getName().equalsIgnoreCase("adminstorage")) {
                if(args.length>0){
                    if (args[0]=="list"){
                        for (ItemStack itemStack:cache.getPlayerStoragedatafromstorageMap(player).getItems()){
                           if (itemStack!=null){
                               player.sendMessage(itemStack.getType().name()+" "+itemStack.getAmount());
                           }
                        }
                    }
                }
            }else if (command.getName().equalsIgnoreCase("backpack")){
                if(args.length>0){
                    if (args[0].equalsIgnoreCase("small")){
                        player.give(BACKPACK.SMALL_BACKPACK.Item());
                    }
                }
            }else if (command.getName().equalsIgnoreCase("enderchest")){
                int page;
                if (args.length>0){
                    page = Integer.parseInt(args[0]);
                }else {
                    page=1;
                }
                plugin.getEnderChestMenu().openEnderChestUI(page,player);
            }
        }

        return false;
    }
    private boolean executesetlevel(String arg1, String arg2, Player player){
        Player player1 = Bukkit.getPlayer(arg1);
        int x = Integer.parseInt(arg2);
        if (!player1.isOnline()||player1==null){
            player.sendMessage("Invalid Player "+arg1);
            return false;
        } else if (x<0||x>8) {
            player.sendMessage("You can set enderchest slots from 0-8 only!");
            return false;
        }else {
            cache.getPlayerStoragedatafromstorageMap(player1).setLevel(x);
            return true;
        }
    }
}
