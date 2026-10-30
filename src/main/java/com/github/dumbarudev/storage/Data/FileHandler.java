package com.github.dumbarudev.storage.Data;

import com.github.dumbarudev.storage.HyperStoragePlugin;
import org.bukkit.Bukkit;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class FileHandler {
    private final HyperStoragePlugin plugin;

    public FileHandler(HyperStoragePlugin plugin) {
        this.plugin = plugin;
    }
    public void createDirectoryAsync(File directory) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            if (!directory.exists()) {
                directory.mkdirs();
            }
        });
    }

    /**
     * Ensures a file exists, creating its parent directories and the file itself if they don't exist.
     */
    public void createFileAsync(File file) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                File parent = file.getParentFile();
                if (parent != null && !parent.exists()) {
                    parent.mkdirs(); // Always create parent directories first!
                }
                if (!file.exists()) {
                    file.createNewFile();
                }
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE, "Could not create file: " + file.getName(), e);
            }
        });
    }

    public void checkOrCreateFile(File file){
        Bukkit.getScheduler().runTaskAsynchronously(plugin,()->{
            if (!file.exists())
                createFileAsync(file);
        });
    }

    public void checkOrCreateDirectory(File file){
        Bukkit.getScheduler().runTaskAsynchronously(plugin,()->{
            if (!file.exists())
                createDirectoryAsync(file);
        });
    }
}