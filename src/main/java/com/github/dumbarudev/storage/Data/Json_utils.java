package com.github.dumbarudev.storage.Data;

import com.github.dumbarudev.storage.HyperStorage.core.Storage;
import com.github.dumbarudev.storage.HyperStoragePlugin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.List;

public class Json_utils {
    public static void savefile(HyperStoragePlugin plugin, File file, Storage storage) {
        // 1. Extract slot size and encode items to Base64 safely on the main thread FIRST
        int allowedSlots = storage.getAllowed_slots_size();
        String base64Data = Base64utils.itemStackListToBase64(storage.getItems());
        int level= storage.getLevel();

        // 2. Now run only the file writing asynchronously
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonObject rootdata = new JsonObject();
            rootdata.addProperty("level",level);
            rootdata.addProperty("allowed_slots", allowedSlots);
            rootdata.addProperty("Base64_enderchest_data", base64Data);

            String jsonString = gson.toJson(rootdata);
            CompressionManager.toFile(file,jsonString);
//            try {
//                Files.writeString(Path.of(file.getPath()), jsonString);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
        });
    }

    public static Storage loadfie(File file) {
        if (!file.exists()) {
            Storage storage=new Storage(1);
            storage.setLevel(0);
            return storage;
        }
        //            String jsoncontent = Files.readString(Path.of(file.getPath()));
        String jsoncontent = CompressionManager.fromFile(file);
        Gson gson = new Gson();
        JsonObject rootData = gson.fromJson(jsoncontent, JsonObject.class);
        int level= rootData.get("level").getAsInt();
        int allowed_slot = rootData.get("allowed_slots").getAsInt();
        String itemsbase64 = rootData.get("Base64_enderchest_data").getAsString();

        List<ItemStack> items = Base64utils.itemStackListFromBase64(itemsbase64);
        Storage storage = new Storage(allowed_slot, items);
        storage.setLevel(level);
        return storage;
    }
}
