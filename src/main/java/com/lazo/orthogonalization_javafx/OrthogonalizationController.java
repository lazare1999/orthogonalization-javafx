package com.lazo.orthogonalization_javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.HashMap;

import static com.lazo.orthogonalization_javafx.algorithm.algorithm.ortAlgorithm;

public class OrthogonalizationController {

    public TextArea matrix;
    public TextArea rValues;

    @FXML
    private Label computeText;

    @FXML
    protected void computeButton() {

        try {

            var rV = getRv();
            if (rV ==null) {
                computeText.setText("შეიყვანეთ საიმედოობები");
            }

            var listX = createListx();
            if (listX ==null) {
                computeText.setText("შეიყვანეთ მატრიცა");
            }

            var algAns = ortAlgorithm(listX, rV);

            computeText.setText(String.valueOf(algAns));
        } catch (Exception e) {
            computeText.setText("გაუთვალისწინებელი შეცდომა");
        }




    }

    private HashMap<Integer, Double> getRv() {

        var rValuesString = rValues.getText();

        if (rValuesString ==null || rValuesString.isEmpty()) {
            return null;
        }

        var rows = rValuesString.split("\\n");

        HashMap<Integer, Double> rV = new HashMap<>();

        for (var r : rows) {

            var rIndex = r.substring(0, r.indexOf(","));
            var rvalue = r.substring(r.indexOf(",")+1).trim();

            rV.put(Integer.valueOf(rIndex), Double.valueOf(rvalue));
        }
        return rV;
    }


    private ArrayList<HashMap<Integer, Boolean>> createListx() {


        var matrixString = matrix.getText();

        if (matrixString ==null || matrixString.isEmpty()) {
            return null;
        }

        var rows = matrixString.split("\\n");


        ArrayList<HashMap<Integer, Boolean>> listX = new ArrayList<>();


        for (var r : rows) {
            HashMap<Integer, Boolean> row = new HashMap<>();

            var el = r.split(",");
            for (var ell : el) {

                if (ell.isEmpty())
                    continue;

                if (ell.contains("'")) {
                    row.put(Integer.valueOf(ell.substring(0, r.indexOf("'")).trim()), false);
                } else {
                    row.put(Integer.valueOf(ell.trim()), true);
                }

            }
            listX.add(row);
        }

        return listX;

    }

}
