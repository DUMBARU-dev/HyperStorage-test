package com.github.dumbarudev.storage.HyperStorage.core.UI;

import com.github.dumbarudev.storage.Data.Cache;
import com.github.dumbarudev.storage.Data.Objects.EnderChestPageHolder;
import com.github.dumbarudev.storage.HyperStorage.core.Storage;
import com.github.dumbarudev.storage.HyperStorage.api.EnderChestMenu;
import com.github.dumbarudev.storage.HyperStoragePlugin;
import com.github.dumbarudev.storage.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class EnderChestUI implements EnderChestMenu {
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
    @Override
    public void openEnderChestUI(int page, Player player){
        Storage storage = cache.getPlayerStoragedatafromstorageMap(player);
//        int allowedpages= StorageCalculator.getallowedpages(storage.getAllowed_slots_size());
        int allowedpages = storage.getAllowed_pages();
        if (page>allowedpages){
            player.sendMessage(ChatColor.RED+"Cant find menu");
            return;
        }
        Inventory inventory = createSkeletonInv(page,allowedpages);
        addplayeritems(page,inventory,player,storage);
        player.openInventory(inventory);
        player.playSound(player, Sound.BLOCK_ENDER_CHEST_OPEN,1f,0.5f);
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
        Inventory inventory = Bukkit.createInventory(new EnderChestPageHolder(currentpage),size,"Ender Chest ("+currentpage+"/"+allowedpages+")");
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
