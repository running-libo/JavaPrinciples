package com.example.javaprinciples.lrucache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapUse {

    public static void accessLinkedHashMap() {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>(0,0.75f, true);
        map.put(0, 0);
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);

        map.get(2);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "----" + entry.getValue());
        }
    }
}
