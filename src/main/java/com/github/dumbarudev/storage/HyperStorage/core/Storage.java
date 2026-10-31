package com.github.dumbarudev.storage.HyperStorage.core;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Storage {
    private int allowed_slots_size;
    private int level;
    private List<ItemStack> items;
    private int allowed_pages;

    public Storage(int allowed_slots_size,List<ItemStack> items){
        this.allowed_slots_size=allowed_slots_size;
        if (items == null) {
            this.items = new ArrayList<>(Collections.nCopies(405, null));
        } else {
            this.items = new ArrayList<>(items);
            while (this.items.size() < 405) {
                this.items.add(null);
            }
        }
        level=-1;
        allowed_pages=StorageCalculator.getallowedpages(allowed_slots_size);
    }
    public Storage(int level){
        setLevel(level);
        this.level=level;
        allowed_pages=StorageCalculator.getallowedpages(allowed_slots_size);
        this.items = new ArrayList<>(Collections.nCopies(405, null));
    }


    public void setAllowed_slots_size(int allowed_slots_size) {
        this.allowed_slots_size = allowed_slots_size;
    }

    public void setItems(List<ItemStack> items) {
        this.items = items;
    }

    public int getAllowed_pages() {
        return allowed_pages;
    }

    public int getAllowed_slots_size() {
        return allowed_slots_size;
    }

    public List<ItemStack> getItems() {
        return items;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        if (level==0){
            allowed_slots_size=54;
        } else if (level==1) {
            allowed_slots_size=99;
        }else if (level==2) {
            allowed_slots_size=144;
        }else if (level==3) {
            allowed_slots_size=189;
        }else if (level==4) {
            allowed_slots_size=234;
        }else if (level==5) {
            allowed_slots_size=279;
        }else if (level==6) {
            allowed_slots_size=324;
        }else if (level==7) {
            allowed_slots_size=369;
        }else if (level==8) {
            allowed_slots_size=405;
        }
        allowed_pages=StorageCalculator.getallowedpages(allowed_slots_size);
    }
}
