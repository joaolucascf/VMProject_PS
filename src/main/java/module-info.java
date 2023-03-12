module com.vm.ps.vmproject_ps {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.vm.ps.vmproject_ps to javafx.fxml;
    exports com.vm.ps.vmproject_ps;
}