module com.example.booksdatabase {

    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.apache.poi.ooxml;

    opens com.example.booksdatabase to javafx.fxml;
    exports com.example.booksdatabase;
}