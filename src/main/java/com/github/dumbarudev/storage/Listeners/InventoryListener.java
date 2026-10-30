package com.github.dumbarudev.storage.Listeners;

import com.github.dumbarudev.storage.Data.Cache;
import com.github.dumbarudev.storage.Data.Objects.Storage;
import com.github.dumbarudev.storage.HyperStoragePlugin;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class InventoryListener implements Listener {
    private final HyperStoragePlugin plugin;
    private Cache cache;
    public InventoryListener(HyperStoragePlugin plugin){
        this.plugin=plugin;
        cache=plugin.getCache();
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if (event.getWhoClicked() instanceof Player player){
            int slot = event.getSlot();
            String title =event.getView().getTitle();
            ItemStack item = event.getCurrentItem();
            if (cache.doplayerexistinstorageinventory(player)){
                if (title.equalsIgnoreCase("Storage")) {
                    executestorageclick(event);
                    event.setCancelled(true);
                }
            }
            if (title.contains("Ender Chest (")){
                   if (event.getClickedInventory().equals(event.getView().getTopInventory())){
                       if (slot==0){
                           player.closeInventory();
                           event.setCancelled(true);
                       }else if(slot==1){
                           plugin.getStorageUI().openStorageMainUI(player);
                           event.setCancelled(true);
                       }else if (slot<9){
                           event.setCancelled(true);
                       }
                   }


            }
        }
    }
    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event){
      Player player= (Player) event.getPlayer();}
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event){
        Inventory inventory =event.getInventory();
        Player player = (Player) event.getPlayer();
        String title=event.getView().getTitle();
        if (title.equalsIgnoreCase("Storage")&&cache.doplayerexistinstorageinventory(player)){
            cache.remoneplayertostorageinventorylist(player);
        }else if (title.contains("Ender Chest (")){
            char[] text = new char[1];
            text[0]=title.charAt(13);
            int x= Integer.parseInt(String.copyValueOf(text));
            cachedatafromenderchest(x,player,inventory);
        }
    }
    private void executestorageclick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        ItemMeta meta = item.getItemMeta();
        if (meta!=null){
            PersistentDataContainer container=meta.getPersistentDataContainer();
            if (item.getType()== Material.PURPLE_STAINED_GLASS_PANE){
                NamespacedKey namespacedKey = new NamespacedKey(plugin,"storage_page");
                if (meta.getPersistentDataContainer().has(namespacedKey)){
                    int page= container.get(namespacedKey, PersistentDataType.INTEGER);
                    plugin.getEnderChestUI().openEnderChestUI(page,player);
                }
            }
            if (container.has(new NamespacedKey(plugin,"close"))){
                if (container.get(new NamespacedKey(plugin, "close"), PersistentDataType.BOOLEAN)) {
                    player.closeInventory();
                }
            }
        }

    }

    private void cachedatafromenderchest(int currentpage, Player player, Inventory inventory) {
        Storage storage = cache.getPlayerStoragedatafromstorageMap(player);
        List<ItemStack> itemStackList = storage.getItems();

        // Correct formula: Page 1 starts at 0. Page 2 starts at 45. Page 3 starts at 90, etc.
        int startIndex = (currentpage - 1) * 45;

        for (int i = 0; i < inventory.getSize()-9; i++) {
            int targetStorageIndex = startIndex + i;

            // inventory.getItem(i + 9) grabs from your GUI container slots
            ItemStack item = inventory.getItem(i + 9);

            itemStackList.set(targetStorageIndex, item);
        }
    }
}
