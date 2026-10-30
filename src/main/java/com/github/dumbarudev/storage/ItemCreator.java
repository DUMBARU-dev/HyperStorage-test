package com.github.dumbarudev.storage;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.UUID;

public class ItemCreator {
    public static ItemStack createItem(Material material,String display_name){
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(display_name);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack createnonameitem(Material material){
        ItemStack itemStack= new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setHideTooltip(true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack addPersistantkeyInt(HyperStoragePlugin plugin, String key, int data, ItemStack itemStack){
        NamespacedKey namespacedKey = new NamespacedKey(plugin,key);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.getPersistentDataContainer().set(namespacedKey,PersistentDataType.INTEGER,data);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack addPersistantkeyBoolean(HyperStoragePlugin plugin, String key, boolean data, ItemStack itemStack){
        NamespacedKey namespacedKey = new NamespacedKey(plugin,key);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.getPersistentDataContainer().set(namespacedKey,PersistentDataType.BOOLEAN,data);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack createFromBase64(String base64Texture) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) head.getItemMeta();

        if (meta != null) {
            // Generate a deterministic or random UUID for the un-named profile
            PlayerProfile profile = Bukkit.createProfile(UUID.randomUUID());
            profile.setProperty(new ProfileProperty("textures", base64Texture));

            meta.setPlayerProfile(profile);
            head.setItemMeta(meta);
        }

        return head;
    }

    /**
     * Creates a custom player head using a direct texture URL.
     *
     * @param skinUrl Direct URL (e.g. "http://textures.minecraft.net/texture/...")
     * @return The configured player head ItemStack
     */
    public static ItemStack createFromUrl(String skinUrl) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) head.getItemMeta();

        if (meta != null) {
            PlayerProfile profile = Bukkit.createProfile(UUID.randomUUID());
            try {
                URL url = URI.create(skinUrl).toURL();
                profile.getTextures().setSkin(url);
                meta.setPlayerProfile(profile);
                head.setItemMeta(meta);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        return head;
    }

    /**
     * Creates a player head using a player's username (fetches profile async-friendly if cached).
     *
     * @param username The player's name
     * @return The player head ItemStack
     */
    public static ItemStack createFromUsername(String username) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) head.getItemMeta();

        if (meta != null) {
            PlayerProfile profile = Bukkit.createProfile(username);
            meta.setPlayerProfile(profile);
            head.setItemMeta(meta);
        }

        return head;
    }
}

