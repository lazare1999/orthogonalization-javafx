package com.lazo.orthogonalization_javafx.algorithm;

import java.util.*;

/**
 * Created by Lazo on 2021-12-08
 */

public class algorithm extends OrtAlgorithmHelper {

    public static Double ortAlgorithm(ArrayList<HashMap<Integer, Boolean>> listX, HashMap<Integer, Double> rValues) {

        listX.sort(Comparator.comparing(a -> Collections.min(a.keySet())));
        listX.sort(Comparator.comparing(HashMap::size));

        List<ArrayList<HashMap<Integer, Boolean>>> kInvertedMatrixList = new ArrayList<>();

        for (int i=0; listX.size()-1 > i; i++) {
            kInvertedMatrixList.add(invertHashMap(listX.get(i)));
        }

        ArrayList<HashMap<Integer, Boolean>> ans = calculateLogicalAnds(listX, kInvertedMatrixList);

        double intAns = 1.0;
        for (var a : ans) {
            double row = 1.0;
            for (var aa : a.entrySet()) {
                var b = rValues.get(aa.getKey());
                if (!aa.getValue())
                    row = row * (1-b);
                else
                    row = row * b;
            }
            intAns +=row;
        }

        intAns= intAns-1.0;

        return intAns;
    }

}
