module bancoInter {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires spring.boot.autoconfigure;
    requires spring.boot;

    opens com.example.bancoInter to javafx.fxml;
    exports com.example.bancoInter;
}