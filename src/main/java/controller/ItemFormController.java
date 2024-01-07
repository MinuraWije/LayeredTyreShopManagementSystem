package controller;

import com.jfoenix.controls.JFXTextField;
import dto.ItemDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.ItemModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class ItemFormController {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txtItemId;

    @FXML
    private JFXTextField txtBrand;

    @FXML
    private JFXTextField txtModel;

    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    private JFXTextField txtQtyOnHand;

    ObservableList<ItemDTO> observableList = FXCollections.observableArrayList();
    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String itemId = txtItemId.getText();

        try {
            boolean isRemoved = ItemModel.delete(itemId);

            if (isRemoved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted successfully").show();
                txtItemId.setText("");
                txtBrand.setText("");
                txtModel.setText("");
                txtUnitPrice.setText("");
                txtQtyOnHand.setText("");
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
        boolean isValidated = validateItem();
        if(isValidated){
            String itemId = txtItemId.getText();
            String brand = txtBrand.getText();
            String model = txtModel.getText();
            Double unitPrice = Double.valueOf(txtUnitPrice.getText());
            Integer qtyOnHand = Integer.valueOf(txtQtyOnHand.getText());

            try {
                boolean isSaved = ItemModel.save(new ItemDTO(itemId, brand,model, unitPrice, qtyOnHand));

                if (isSaved) {

                    new Alert(Alert.AlertType.CONFIRMATION, "Saved  !!!").show();
                    txtItemId.setText("");
                    txtBrand.setText("");
                    txtModel.setText("");
                    txtUnitPrice.setText("");
                    txtQtyOnHand.setText("");
                    observableList.clear();

                } else {
                    new Alert(Alert.AlertType.ERROR, "Not saved  !!!").show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean validateItem() {
        String itemId = txtItemId.getText();
        boolean matches = Pattern.matches("[I][0-9]{3,}",itemId);

        if(!matches){
            new Alert(Alert.AlertType.ERROR, "Invalid item id.").show();
            return false;
        }
        String brand = txtBrand.getText();
        boolean matches1 = Pattern.matches("[A-Za-z]{4,}", brand);

        if (!matches1) {
            new Alert(Alert.AlertType.ERROR, "Invalid brand name.").show();
            return false;
        }
        String model = txtModel.getText();
        boolean matches2 = Pattern.matches("[A-Za-z]{4,}", model);

        if (!matches2) {
            new Alert(Alert.AlertType.ERROR, "Invalid model name.").show();
            return false;
        }
        return true;
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String itemId = txtItemId.getText();
        String brand = txtBrand.getText();
        String model = txtModel.getText();
        Double unitPrice = Double.valueOf(txtUnitPrice.getText());
        Integer qtyOnHand = Integer.valueOf(txtQtyOnHand.getText());

        boolean isUpdated = false;
        try {
            isUpdated = ItemModel.update(new ItemDTO(itemId, brand, model,unitPrice,qtyOnHand));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated successfully").show();
                txtItemId.setText("");
                txtBrand.setText("");
                txtModel.setText("");
                txtUnitPrice.setText("");
                txtQtyOnHand.setText("");
                observableList.clear();

            } else {
                new Alert(Alert.AlertType.ERROR, "Update failed").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void txtItemIdSearchOnAction(ActionEvent event) {
        String itemId = txtItemId.getText();

        try {
            ItemDTO itemDTO= ItemModel.search(itemId);

            if (itemDTO != null) {
                txtItemId.setText(itemDTO.getItemId());
                txtBrand.setText(itemDTO.getBrand());
                txtModel.setText(itemDTO.getModel());
                txtUnitPrice.setText(String.valueOf(itemDTO.getUnitPrice()));
                txtQtyOnHand.setText(String.valueOf(itemDTO.getQtyOnHand()));
            }else {
                new Alert(Alert.AlertType.ERROR,"Invalid ID").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnViewOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/viewItemForm.fxml"));
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
    void itemBtnOnAction(ActionEvent event) {

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
    void supplierBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/supplierForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
    }


}
