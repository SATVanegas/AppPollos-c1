module com.intfinity.myapi.pollonico {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    opens com.intfinity.myapi.pollonico.interfaces to javafx.base;
    opens com.intfinity.myapi.pollonico.Help to javafx.base;
    opens com.intfinity.myapi.pollonico to javafx.fxml;
    exports com.intfinity.myapi.pollonico;
    exports com.intfinity.myapi.pollonico.Motor;
    opens com.intfinity.myapi.pollonico.Motor to javafx.fxml;
    exports com.intfinity.myapi.pollonico.Controllers;
    opens com.intfinity.myapi.pollonico.Controllers to javafx.fxml;
    opens com.intfinity.myapi.pollonico.Models to javafx.base;
    exports com.intfinity.myapi.pollonico.Models;
    exports com.intfinity.myapi.pollonico.Help;
    exports com.intfinity.myapi.pollonico.interfaces;
}