package com.lazo.orthogonalization_javafx.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Lazo on 2021-12-08
 */

public class LazoUtils {

    public static <K, V> Map<K, V> copyMap(Map<K, V> original) {
        return new HashMap<>(original);
    }

    static ArrayList<HashMap<Integer, Boolean>> invertHashMap(HashMap<Integer, Boolean> k) {
        ArrayList<HashMap<Integer, Boolean>> invertedK = new ArrayList<>();

        Iterator<Map.Entry<Integer, Boolean>> it = k.entrySet().iterator();
        HashMap<Integer, Boolean> commonRow = new HashMap<>();
        int i =1;

        while (it.hasNext()) {
            Map.Entry<Integer, Boolean> pair = it.next();

            HashMap<Integer, Boolean> row = new HashMap<>();

            if (i!=1) {
                row = (HashMap<Integer, Boolean>) copyMap(commonRow);
            }

            commonRow.put(pair.getKey(), pair.getValue());
            row.put(pair.getKey(), !pair.getValue());


            invertedK.add(row);
            i++;
        }

        return invertedK;
    }

}
