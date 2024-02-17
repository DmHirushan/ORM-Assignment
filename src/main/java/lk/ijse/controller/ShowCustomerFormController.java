package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.entity.Customer;
import lk.ijse.util.Ui;
import org.hibernate.Session;

import java.io.IOException;

public class ShowCustomerFormController {
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colSalary;
    public TableColumn colMobileNo;
    public TableView tblCustomers;

    public void initialize() throws IOException {
        setCellValuefactory();
        loadCustomers();
    }

    private void loadCustomers() throws IOException {
        ObservableList<Customer> obList = FXCollections.observableArrayList();
        Session session = SessionFactoryConfig.getInstance().getSession();
        obList.addAll(session.createQuery("from customer ", Customer.class).list());
        tblCustomers.setItems(obList);
    }

    private void setCellValuefactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colMobileNo.setCellValueFactory(new PropertyValueFactory<>("mobileNos"));
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        new Ui().setUi("customerForm", actionEvent);
    }
}
