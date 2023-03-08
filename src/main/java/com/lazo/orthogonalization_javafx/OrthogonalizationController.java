package com.lazo.orthogonalization_javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static com.lazo.orthogonalization_javafx.algorithm.algorithm.*;

public class OrthogonalizationController {

    public TextArea matrix;
    public TextArea rValues;
    public Label y_matrix;
    public Label weights;
    public TextField element;

    @FXML
    private Label computeText;

    @FXML
    protected void computeButton() {

        try {

            var rV = getRv();
            if (rV ==null) {
                computeText.setText("შეიყვანეთ საიმედოობები");
                return;
            }

            var listX = createListx();
            if (listX ==null) {
                computeText.setText("შეიყვანეთ მატრიცა");
                return;
            }

            var algAns = ortAlgorithm_compute(listX, rV);

            computeText.setText(String.valueOf(algAns));
        } catch (Exception e) {
            computeText.setText("გაუთვალისწინებელი შეცდომა");
        }




    }

    @FXML
    protected void printMatrix() {
        try {


            var listX = createListx();
            if (listX ==null) {
                y_matrix.setText("შეიყვანეთ მატრიცა");
                return;
            }

            var algAns = ortAlgorithm_y(listX);

            StringBuilder text = new StringBuilder();
            for (var a : algAns) {

                StringBuilder row = new StringBuilder();
                for (var aa : a.entrySet()) {

                    if (aa.getValue())
                        row.append(aa.getKey()).append("  ");
                    else
                        row.append(aa.getKey()).append("`").append(" ");

                }

                text.append(row).append("\r\n");
            }


            y_matrix.setText(String.valueOf(text));
        } catch (Exception e) {
            y_matrix.setText("გაუთვალისწინებელი შეცდომა");
        }

    }

    @FXML
    protected void calculateWeights() {
        var listX = createListx();
        if (listX ==null) {
            y_matrix.setText("შეიყვანეთ მატრიცა");
            return;
        }


        weights.setText(String.valueOf(calculateWeight(ortAlgorithm_y(listX), Integer.valueOf(element.getText()))));
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
            LinkedHashMap<Integer, Boolean> row = new LinkedHashMap<>();

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
