module com.example.netflexe {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.netflexe.Vue to javafx.fxml;
    opens com.example.netflexe.Model to javafx.fxml;
    opens com.example.netflexe.Controller to javafx.fxml;
    exports com.example.netflexe.Vue;
    exports com.example.netflexe.Model;
    exports com.example.netflexe.Controller;
}