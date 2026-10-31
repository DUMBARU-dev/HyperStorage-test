package com.github.dumbarudev.storage.Data.Objects;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class EnderChestPageHolder implements InventoryHolder {
    private Inventory inventory;
    private int page;
    public EnderChestPageHolder(int page){
        this.page=page;
    }

    public int getPage() {
        return page;
    }
    @Override
    public Inventory getInventory() {
        return this.inventory;
    }
}
