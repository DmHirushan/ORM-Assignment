package lk.ijse.util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.controller.CustomerFormController;

import java.io.IOException;

public class Ui {
    public void setUi(String location, ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/"+location+".fxml"));
        Parent customerFormParent = loader.load();

        // Access the controller for further interaction if needed
        //CustomerFormController customerFormController = loader.getController();

        // Get the stage from the button's scene and set the new content
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(customerFormParent));
        stage.show();
    }
}
