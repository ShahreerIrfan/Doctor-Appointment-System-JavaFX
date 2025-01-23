module com.doctor.doctorappointment {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens com.doctor.doctorappointment.controller to javafx.fxml;
    opens com.doctor.doctorappointment.model to javafx.base; // Add this line to open the model package to javafx.base

    exports com.doctor.doctorappointment;
}
