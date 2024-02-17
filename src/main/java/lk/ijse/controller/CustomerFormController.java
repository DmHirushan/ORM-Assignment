package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.embeded.MobileNumber;
import lk.ijse.embeded.NameIdentifier;
import lk.ijse.entity.Customer;
import lk.ijse.util.Ui;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomerFormController {

    public TextField txtAge;
    public TextField txtAddress;
    public TextField txtSalary;
    public TextField txtHomeNumber;
    public TextField txtMobileNumber;
    public TextField txtFname;
    public TextField txtMname;
    public TextField txtLname;
    public TextField cusId;

    public void initialize(){

    }

    public CustomerFormController() throws IOException {
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        new Ui().setUi("dashboardForm", actionEvent);
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws IOException {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        System.out.println(txtHomeNumber.getText());
        MobileNumber mobileNumber = new MobileNumber("Home", txtHomeNumber.getText());
        MobileNumber mobileNumber1 = new MobileNumber("Mobile", txtMobileNumber.getText());
        List<MobileNumber> mobileNumberList = new ArrayList<>();
        mobileNumberList.add(mobileNumber);
        mobileNumberList.add(mobileNumber1);
        Customer customer = new Customer(
            new NameIdentifier(txtFname.getText(), txtMname.getText(), txtLname.getText()),
            Integer.parseInt(txtAge.getText()),
                txtAddress.getText(),
                Double.parseDouble(txtSalary.getText()),
                mobileNumberList
        );
        session.save(customer);
        transaction.commit();
        session.close();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws IOException {
        new Ui().setUi("updateCustomerForm", actionEvent);
    }

    public void btnViewOnAction(ActionEvent actionEvent) throws IOException {
        new Ui().setUi("showCustomerForm", actionEvent);
    }
}
