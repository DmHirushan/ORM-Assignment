package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
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

public class UpdateCustomerFormController {
    public TextField txtFname;
    public TextField txtMname;
    public TextField txtLname;
    public TextField txtAge;
    public TextField txtAddress;
    public TextField txtSalary;
    public TextField txtHomeNumber;
    public TextField txtMobileNumber;
    public TextField txtId;
    //private Session session = SessionFactoryConfig.getInstance().getSession();

    public UpdateCustomerFormController() throws IOException {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws IOException {

        Session session = SessionFactoryConfig.getInstance().getSession();
        Customer customer = session.get(Customer.class, Integer.parseInt(txtId.getText()));
        Transaction transaction = session.beginTransaction();
        customer.setName(new NameIdentifier(txtFname.getText(), txtMname.getText(), txtLname.getText()));
        customer.setAge(Integer.parseInt(txtAge.getText()));
        customer.setAddress(txtAddress.getText());
        customer.setSalary(Double.parseDouble(txtSalary.getText()));
        MobileNumber mobileNumber = new MobileNumber("Home", txtHomeNumber.getText());
        MobileNumber mobileNumber1 = new MobileNumber("Mobile", txtMobileNumber.getText());
        List<MobileNumber> mobileNumberList = new ArrayList<>();
        mobileNumberList.add(mobileNumber);
        mobileNumberList.add(mobileNumber1);
        customer.setMobileNos(mobileNumberList);
        transaction.commit();
        session.update(customer);
        session.close();

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws IOException {
        boolean x = false;
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.get(Customer.class, Integer.parseInt(txtId.getText()));
        session.delete(customer);
        transaction.commit();
        x = true;
        if (x == true){
            new Alert(Alert.AlertType.CONFIRMATION, "Customer Deleted!").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
        }
        session.close();
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        new Ui().setUi("customerForm", actionEvent);
    }

    public Customer txtIdOnAction(ActionEvent actionEvent) throws IOException {
        Customer customer = searchCustomer(Integer.parseInt(txtId.getText()));
        if (customer != null){
            setDataToFields(customer);
            return customer;
            //session.close();
        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid Id!").show();
            return null;
        }
    }

    private void setDataToFields(Customer customer) {
        NameIdentifier name = customer.getName();
        txtFname.setText(name.getFirstName());
        txtMname.setText(name.getMiddleName());
        txtLname.setText(name.getLastName());
        txtAge.setText(String.valueOf(customer.getAge()));
        txtAddress.setText(customer.getAddress());
        txtSalary.setText(String.valueOf(customer.getSalary()));
        List<MobileNumber> mobileNos = customer.getMobileNos();
        txtHomeNumber.setText(String.valueOf(mobileNos.get(0).getNumber()));
        txtMobileNumber.setText(String.valueOf(mobileNos.get(1).getNumber()));
    }

    private Customer searchCustomer(int id) throws IOException {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Customer customer = session.get(Customer.class, id);
        if (customer != null){
            return customer;
        }else {
            session.close();
        }
        return null;
    }

}
