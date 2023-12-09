package edu.hw6.Task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {
    private final Map<String, String> memoryMap;

    private final String file;

    private boolean immediatelySave;

    public DiskMap(String file) {
        this(file, false);
    }

    public DiskMap(String file, boolean immediatelySave) {
        this.file = file;
        this.immediatelySave = immediatelySave;
        memoryMap = new HashMap<>();
    }

    public void load() {
        try (var reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                var entry = line.split(":");
                if (entry.length == 2) {
                    memoryMap.put(entry[0], entry[1]);
                } else {
                    throw new IOException();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void save() {
        try (var writer = new BufferedWriter(new FileWriter(file))) {
            for (var entry : memoryMap.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int size() {
        return memoryMap.size();
    }

    @Override
    public boolean isEmpty() {
        return memoryMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return memoryMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return memoryMap.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return memoryMap.get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        String result = memoryMap.put(key, value);
        if (immediatelySave) {
            save();
        }
        return result;
    }

    @Override
    public String remove(Object key) {
        boolean contains = memoryMap.containsKey(key);
        String result = memoryMap.remove(key);
        if (contains && immediatelySave) {
            save();
        }
        return result;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        memoryMap.putAll(m);
        if (immediatelySave) {
            save();
        }
    }

    @Override
    public void clear() {
        memoryMap.clear();
        if (immediatelySave) {
            save();
        }
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return memoryMap.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return memoryMap.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return memoryMap.entrySet();
    }

    public boolean isImmediatelySave() {
        return immediatelySave;
    }

    public void setImmediatelySave(boolean immediatelySave) {
        this.immediatelySave = immediatelySave;
    }

    public String getFile() {
        return file;
    }
}
