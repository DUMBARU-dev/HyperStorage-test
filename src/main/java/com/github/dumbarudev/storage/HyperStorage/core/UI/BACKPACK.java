package com.github.dumbarudev.storage.HyperStorage.core.UI;

import com.github.dumbarudev.storage.HyperStoragePlugin;
import com.github.dumbarudev.storage.ItemCreator;
import com.github.dumbarudev.storage.ServiceRegistry;
import org.bukkit.inventory.ItemStack;

public enum BACKPACK {
    SMALL_BACKPACK(ItemCreator.createFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHBzOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzIxZDgzN2NhMjIyY2JjMGJjMTI0MjZmNWRhMDE4YzNhOTMxYjQwNjAwODgwMDk2MGE5ZGYxMTJhNTk2ZTdkNjIifX19")),
    MEDIUM_BACKPACK(ItemCreator.createFromBase64(""));
    private ItemStack item;
    BACKPACK(ItemStack item){
        this.item=ItemCreator.addPersistantkeyID(ServiceRegistry.get(HyperStoragePlugin.class),"ID","SMALL_BACKPACK",item);
    }

    public ItemStack Item() {
        return item;
    }
}
