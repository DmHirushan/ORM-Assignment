package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.util.Ui;

import java.io.IOException;

public class DashboardFormController {
    public Button btnCustomer;
    public AnchorPane dashboardContext;

    public void initialize(){
        //btnCustomer.getStyleClass().add("buttonstyle");
        btnCustomer.getStyleClass().add("button");
    }

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        new Ui().setUi("customerForm", actionEvent);
    }


}
