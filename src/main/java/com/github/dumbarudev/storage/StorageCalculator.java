package com.github.dumbarudev.storage;

public class StorageCalculator {
    public static int getallowedpages(int totalslots){
        int full_pages=totalslots/45;
        int remaining_slots=totalslots%45;
        if (remaining_slots==0){
            return full_pages;
        }else {
            return full_pages+1;
        }
    }
}
