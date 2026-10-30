package com.github.dumbarudev.storage.Data;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Base64utils {
    public static String itemStackListToBase64(List<ItemStack> items) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            try (BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream)) {
                // Write the total number of items first
                dataOutput.writeInt(items.size());

                // Write each item (can be null for empty slots)
                for (ItemStack item : items) {
                    dataOutput.writeObject(item);
                }
            }
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Deserializes a Base64 string back into a List of ItemStacks.
     * Automatically pads or ensures safety if the string is empty or invalid.
     */
    public static List<ItemStack> itemStackListFromBase64(String data) {
        List<ItemStack> items = new ArrayList<>();

        if (data == null || data.isEmpty()) {
            // Fallback default size (e.g., 405 nulls)
            return new ArrayList<>(Collections.nCopies(405, null));
        }

        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            try (BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream)) {
                int size = dataInput.readInt();
                for (int i = 0; i < size; i++) {
                    items.add((ItemStack) dataInput.readObject());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Fallback safety if decoding fails
            return new ArrayList<>(Collections.nCopies(405, null));
        }

        // Ensure it always matches your required size (e.g., 405 slots)
        while (items.size() < 405) {
            items.add(null);
        }

        return items;
    }
}
