package com.github.dumbarudev.storage.HyperStorage.core.UI;

import com.github.dumbarudev.storage.Data.Cache;
import com.github.dumbarudev.storage.HyperStorage.core.Storage;
import com.github.dumbarudev.storage.HyperStorage.api.StorageMenu;
import com.github.dumbarudev.storage.HyperStorage.core.StorageCalculator;
import com.github.dumbarudev.storage.HyperStoragePlugin;
import com.github.dumbarudev.storage.ItemCreator;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class StorageUI implements StorageMenu {
    private final HyperStoragePlugin plugin;
    private final Cache cache;
    private final ItemStack[] skeletonitems = new ItemStack[54];
    public StorageUI(HyperStoragePlugin plugin){
        this.plugin=plugin;
        this.cache=plugin.getCache();
        setSkeletonitems();
    }

    public void setSkeletonitems() {
        ItemStack filler= UIItems.FILLER_PANE.Item();
        for (int i=0;i<54;i++){
            if (i==4){
                skeletonitems[i]= ItemCreator.createItem(Material.ENDER_CHEST, ChatColor.GREEN+"Ender Chest");
            }else if (i>=9&&i<=17){
                skeletonitems[i]=ItemCreator.createItem(Material.RED_STAINED_GLASS_PANE,ChatColor.RED+"Locked Page");
            }else if(i==22){
                skeletonitems[i]=ItemCreator.createItem(Material.CHEST,ChatColor.GREEN+"Backpacks");
            }else if(i>=27&&i<=44){
                skeletonitems[i]=ItemCreator.createItem(Material.GRAY_DYE,"");
            }else if (i==49){
                skeletonitems[i]=ItemCreator.addPersistantkeyBoolean(plugin,"close",true,ItemCreator.createItem(Material.BARRIER,ChatColor.RED+"Close"));
            }else {
                skeletonitems[i]=filler;
            }

        }
    }
    @Override
    public boolean openStorageMainUI(Player player){
        Storage storage = cache.getPlayerStoragedatafromstorageMap(player);
        int slot = storage.getAllowed_slots_size();
        int allowed_pages= StorageCalculator.getallowedpages(slot);
        player.openInventory(create_inventory(allowed_pages));
        return isViewingStorage(player);
    }
    private Inventory create_inventory(int allowed_pages){
        Inventory inventory = Bukkit.createInventory(null,54, Component.text("Storage"));
        for (int i=0;i<54;i++){
            inventory.setItem(i,skeletonitems[i]);
        }
        for (int i=0;i<allowed_pages;i++){
            ItemStack x = ItemCreator.addPersistantkeyInt(
                    plugin,
                    "storage_page",
                    i + 1,
                    ItemCreator.createItem(Material.PURPLE_STAINED_GLASS_PANE, ChatColor.GREEN + "Ender Chest Page " + (i + 1))
            );
            inventory.setItem(i+9,x);
        }
        return inventory;
    }

    public static boolean isViewingStorage(Player player) {
        // Get what the player currently has open
        var openView = player.getOpenInventory();

        // The "top" inventory is the container/GUI they are looking at (not their own hotbar/inventory)
        Inventory topInventory = openView.getTopInventory();

        // 1. Check by Inventory Title (make sure it matches your GUI title)
        if (openView.getTitle().contains("Storage")&&topInventory!=null) {
            return true;
        }

        // 2. BETTER: Check by Custom Inventory Holder (if you use a custom class for your GUI holder)
        // if (topInventory.getHolder() instanceof StorageInventoryHolder) {
        //     return true;
        // }

        return false;
    }
}
