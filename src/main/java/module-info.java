module com.lazo.orthogonalization_javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.lazo.orthogonalization_javafx to javafx.fxml;
    exports com.lazo.orthogonalization_javafx;
}
