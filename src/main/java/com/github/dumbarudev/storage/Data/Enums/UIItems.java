package com.github.dumbarudev.storage.Data.Enums;

import com.github.dumbarudev.storage.HyperStoragePlugin;
import com.github.dumbarudev.storage.ItemCreator;
import com.github.dumbarudev.storage.ServiceRegistry;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

public enum UIItems {
    FILLER_PANE(ItemCreator.createnonameitem(Material.BLACK_STAINED_GLASS_PANE)),
    CLOSE_MENU(ItemCreator.addPersistantkeyBoolean(ServiceRegistry.get(HyperStoragePlugin.class),"close",true,
            ItemCreator.createItem(Material.BARRIER, ChatColor.RED+"close"))),
    ENDERCHEST_NEXT_PAGE(ItemCreator.createFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjUyN2ViYWU5ZjE1MzE1NGE3ZWQ0OWM4OGMwMmI1YTlhOWNhN2NiMTYxOGQ5OTE0YTNkOWRmOGNjYjNjODQifX19")),
    ENDERCHEST_PREVIOUS_PAGE(ItemCreator.createFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTVkYTQ4NDcyNzI1ODIyNjViZGFjYTM2NzIzN2M5NjEyMmIxMzlmNGU1OTdmYmM2NjY3ZDNmYjc1ZmVhN2NmNiJ9fX0=")),
    ENDERCHEST_LAST_PAGE(ItemCreator.createFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWE5MmFkNDU2Zjc2ZWM3Y2FhMzU5NTkyMmQ1ZmNjMzhkY2E5MjhkYzY3MTVmNzUyZTc0YzhkZGRlMzQ0ZSJ9fX0=")),
    ENDERCHEST_FIRST_PAGE(ItemCreator.createFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGZhNjA1ZTI1ZjRmYzJjZWE1YTc2NmQ3OWE4YmZhMjkwMzEzZTQ1ZDhmNWU5NTdkOTU4YTBmMzNmY2IxNiJ9fX0="));

    private ItemStack item;
    UIItems(ItemStack itemStack){
        item = itemStack;
<<<<<<< HEAD
=======
        //
>>>>>>> 4d63d8b (bug fixes)
    }

    public ItemStack Item() {
        return item;
    }
}
