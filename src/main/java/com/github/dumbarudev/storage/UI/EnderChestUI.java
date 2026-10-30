package com.github.dumbarudev.storage.UI;

import com.github.dumbarudev.storage.Data.Cache;
import com.github.dumbarudev.storage.Data.Enums.UIItems;
import com.github.dumbarudev.storage.Data.Objects.Storage;
import com.github.dumbarudev.storage.HyperStoragePlugin;
import com.github.dumbarudev.storage.ItemCreator;
import com.github.dumbarudev.storage.StorageCalculator;
import org.apache.logging.log4j.util.Base64Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.guieffect.qual.UI;

import java.util.List;

public class EnderChestUI {
    private HyperStoragePlugin plugin;
    private Cache cache;
    private ItemStack[] skeletontiems= new ItemStack[9];
    public EnderChestUI(HyperStoragePlugin plugin){
        this.plugin=plugin;
        cache=plugin.getCache();
        setSkeletontiems();
    }
    private void setSkeletontiems(){
        ItemStack filler = UIItems.FILLER_PANE.Item();
        for (int i=0;i<9;i++){
            if (i==0){
                skeletontiems[i]=UIItems.CLOSE_MENU.Item();
            } else if (i==1) {
                skeletontiems[i]=ItemCreator.addPersistantkeyBoolean(plugin,"filler",true,
                        ItemCreator.createItem(Material.ARROW,"Go back"));
            }else {
                skeletontiems[i]=filler;
            }
        }
        for (ItemStack itemStack: skeletontiems){
            ItemCreator.addPersistantkeyBoolean(plugin,"Menu",true,itemStack);
        }
    }
    public void openEnderChestUI(int currentpage, Player player){
        Storage storage = cache.getPlayerStoragedatafromstorageMap(player);
//        int allowedpages= StorageCalculator.getallowedpages(storage.getAllowed_slots_size());
        int allowedpages = storage.getAllowed_pages();
        Inventory inventory = createSkeletonInv(currentpage,allowedpages);
        addplayeritems(currentpage,inventory,player,storage);
        player.openInventory(inventory);
        player.playSound(player, Sound.BLOCK_CHEST_OPEN,1f,1f);
    }
    public Inventory createSkeletonInv(int currentpage,int allowedpages){
        int lastpageslots = allowedpages%45;
        int sizeoflastpage=(lastpageslots/9+2)*9;
        int size;
        if (currentpage==allowedpages){
            size=sizeoflastpage;
        }else {
            size=54;
        }
        Inventory inventory = Bukkit.createInventory(null,size,"Ender Chest ("+currentpage+"/"+allowedpages+")");
        for (int i=0;i<inventory.getSize();i++){
            if (i<skeletontiems.length){
                if(i==5&&currentpage!=1){
                    inventory.setItem(i,UIItems.ENDERCHEST_FIRST_PAGE.Item());
                }else if(i==6&&currentpage!=1) {
                    inventory.setItem(i,UIItems.ENDERCHEST_PREVIOUS_PAGE.Item());
                }else if(i==7&&currentpage!=allowedpages) {
                    inventory.setItem(i,UIItems.ENDERCHEST_NEXT_PAGE.Item());
                }else if(i==8&&currentpage!=allowedpages) {
                    inventory.setItem(i,UIItems.ENDERCHEST_LAST_PAGE.Item());
                }else {
                    inventory.setItem(i,skeletontiems[i]);
                }

            }
        }
        return inventory;
    }
    private void addplayeritems(int currentpage,Inventory inventory,Player player,Storage storage){
        List<ItemStack> itemStacks = storage.getItems();
        for (int i=0;i<inventory.getSize()-9;i++){
            int slot = i+9;
            int itemcount=(currentpage-1)*45+i;
            inventory.setItem(slot,itemStacks.get(itemcount));
        }
    }
}
