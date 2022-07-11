module com.mycompany.testwithmaven 
{
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires javafx.graphics;
    opens com.mycompany.testwithmaven to javafx.fxml;
    exports com.mycompany.testwithmaven;
}
