package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDTO;
import dto.OrderDTO;
import dto.tm.CustomerTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import dao.CustomerModel;
import dao.OrderModel;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class OrderFormController {

    public ComboBox cmbCustomerId;
    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton homeBtn;

    @FXML
    private JFXButton customerBtn;

    @FXML
    private JFXButton orderBtn;

    @FXML
    private JFXButton itemBtn;

    @FXML
    private JFXButton supplierBtn;

    @FXML
    private JFXButton employeeBtn;

    @FXML
    private JFXButton paymentBtn;

    @FXML
    private JFXButton logoutBtn;

    @FXML
    private AnchorPane orderPane;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnView;

    @FXML
    private JFXTextField txtOrderId;

    @FXML
    private JFXTextField txtQuantity;



    @FXML
    private JFXDatePicker orderDate;

    private CustomerModel customerModel = new CustomerModel();

    ObservableList<OrderDTO> observableList = FXCollections.observableArrayList();

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String orderId = txtOrderId.getText();

        try {
            boolean isRemoved = OrderModel.delete(orderId);

            if (isRemoved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted successfully").show();
                txtOrderId.setText("");
                cmbCustomerId.setValue("");
                orderDate.getValue();
                observableList.clear();

            } else {
                new Alert(Alert.AlertType.ERROR, "Delete failed").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String orderId = txtOrderId.getText();
        String customerId = (String) cmbCustomerId.getValue();
        LocalDate date = orderDate.getValue();


        try {
            boolean isSaved = OrderModel.save(new OrderDTO(orderId, customerId,date));


            if (isSaved) {

                new Alert(Alert.AlertType.CONFIRMATION, "Saved  !!!").show();
                txtOrderId.setText("");
                cmbCustomerId.setValue("");
                orderDate.getValue();
                observableList.clear();

            } else {

                new Alert(Alert.AlertType.ERROR, "Not saved  !!!").show();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String orderId = txtOrderId.getText();
        String customerId = (String) cmbCustomerId.getValue();
        LocalDate date = orderDate.getValue();

        boolean isUpdated = false;
        try {
            isUpdated = OrderModel.update(new OrderDTO(orderId,customerId,date));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated successfully").show();
                txtOrderId.setText("");
                cmbCustomerId.setValue("");
                orderDate.getValue();
                observableList.clear();

            } else {
                new Alert(Alert.AlertType.ERROR, "Update failed").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnViewOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/viewOrderForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
    }

    @FXML
    void cmbCustomerIdOnAction(ActionEvent event) {
        String customerId = (String) cmbCustomerId.getValue();

        try {
            CustomerDTO customerDTO = CustomerModel.search(customerId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCustomerId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<CustomerTM> customerDTOS = customerModel.getAll();

            for (CustomerTM dto : customerDTOS) {
                obList.add(dto.getCustomerId());
            }
            cmbCustomerId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize() {
        loadCustomerId();
    }

    @FXML
    void customerBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/customerForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
    }

    @FXML
    void employeeBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/employeeForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
    }

    @FXML
    void homeBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboardForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
    }

    @FXML
    void itemBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/itemForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
    }

    @FXML
    void logoutBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
    }

    @FXML
    void orderBtnOnAction(ActionEvent event) {

    }

    @FXML
    void paymentBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/paymentForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
    }

    @FXML
    void supplierBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/supplierForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
    }



    @FXML
    void txtOrderIdSearchOnAction(ActionEvent event) {
        String orderId = txtOrderId.getText();

        try {
            OrderDTO orderDTO= OrderModel.search(orderId);

            if (orderDTO != null) {
                txtOrderId.setText(orderDTO.getOrderId());
                cmbCustomerId.setValue(orderDTO.getCustomerId());
                orderDate.setValue(orderDTO.getOrderDate());

            }else {
                new Alert(Alert.AlertType.ERROR,"Invalid ID").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
