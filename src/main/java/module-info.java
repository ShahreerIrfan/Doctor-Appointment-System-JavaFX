module com.doctor.doctorappointment {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.doctor.doctorappointment.controller to javafx.fxml;
    exports com.doctor.doctorappointment;
}