package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import bo.custom.SupplierBO;
import com.jfoenix.controls.JFXTextField;
import dto.SupplierDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import dao.custom.impl.SupplierDAOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class SupplierFormController {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txtSupplierId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtNumber;

    @FXML
    private JFXTextField txtEmail;
    SupplierBO supplierBO = (SupplierBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.SUPPLIER);

    ObservableList <SupplierDTO> observableList = FXCollections.observableArrayList();

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String supplierId = txtSupplierId.getText();

        try {
            boolean isRemoved = supplierBO.deleteSupplier(supplierId);

            if (isRemoved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted successfully").show();
                txtSupplierId.setText("");
                txtName.setText("");
                txtAddress.setText("");
                txtNumber.setText("");
                txtEmail.setText("");
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
        boolean isValidated = validateSupplier();
        if(isValidated){
            String supplierId = txtSupplierId.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            Integer telNum = Integer.valueOf(txtNumber.getText());
            String email = txtEmail.getText();


            try {
                boolean isSaved = supplierBO.saveSupplier(new SupplierDTO(supplierId, name,address, telNum, email));

                if (isSaved) {

                    new Alert(Alert.AlertType.CONFIRMATION, "Saved  !!!").show();
                    txtSupplierId.setText("");
                    txtName.setText("");
                    txtAddress.setText("");
                    txtNumber.setText("");
                    txtEmail.setText("");
                    observableList.clear();

                } else {
                    new Alert(Alert.AlertType.ERROR, "Not saved  !!!").show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean validateSupplier() {
        String supplierId = txtSupplierId.getText();
        boolean matches = Pattern.matches("[S][0-9]{3,}",supplierId);

        if(!matches){
            new Alert(Alert.AlertType.ERROR, "Invalid supplier id.").show();
            return false;
        }
        String name = txtName.getText();
        boolean matches1 = Pattern.matches("[A-Za-z]{4,}", name);

        if (!matches1) {
            new Alert(Alert.AlertType.ERROR, "Invalid supplier name.").show();
            return false;
        }
        return true;
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String supplierId = txtSupplierId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        Integer telNum = Integer.valueOf(txtNumber.getText());
        String email = txtEmail.getText();

        boolean isUpdated = false;
        try {
            isUpdated = supplierBO.updateSupplier(new SupplierDTO(supplierId, name, address,telNum,email));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated successfully").show();
                txtSupplierId.setText("");
                txtName.setText("");
                txtAddress.setText("");
                txtNumber.setText("");
                txtEmail.setText("");
                observableList.clear();

            } else {
                new Alert(Alert.AlertType.ERROR, "Update failed").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void txtSupplierIdSearchOnAction(ActionEvent event) {
        String supplierId = txtSupplierId.getText();

        try {
            SupplierDTO supplierDTO= supplierBO.searchSupplier(supplierId);

            if (supplierDTO != null) {
                txtSupplierId.setText(supplierDTO.getSupplierId());
                txtName.setText(supplierDTO.getName());
                txtAddress.setText(supplierDTO.getAddress());
                txtNumber.setText(String.valueOf(supplierDTO.getTelNum()));
                txtEmail.setText(supplierDTO.getEmail());
            }else {
                new Alert(Alert.AlertType.ERROR,"Invalid ID").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnViewOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/viewSupplierForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
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
    void orderBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/placeOrderForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
    }

    @FXML
    void paymentBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/paymentForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
    }

    @FXML
    void supplierBtnOnAction(ActionEvent event) {

    }

}
