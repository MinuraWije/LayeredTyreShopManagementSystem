package controller;

import dto.tm.SupplierTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import dao.SupplierModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ViewSupplierFormController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<SupplierTM> tblSupplier;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colNumber;

    @FXML
    private TableColumn<?, ?> colEmail;

    private void getAll() {
        try {
            List<SupplierTM> supplierTMS = SupplierModel.getAll();
            ObservableList<SupplierTM> list = FXCollections.observableArrayList();
            for (SupplierTM supplierTM :supplierTMS){
                list.add(
                        new SupplierTM(
                                supplierTM.getSupplierId(),
                                supplierTM.getName(),
                                supplierTM.getAddress(),
                                supplierTM.getTelNum(),
                                supplierTM.getEmail()
                        ));
            }
            tblSupplier.setItems(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {

        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colNumber.setCellValueFactory(new PropertyValueFactory<>("telNum"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();
    }
    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/supplierForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
    }

    @FXML
    void customerBtnOnAction(ActionEvent event) {

    }

    @FXML
    void employeeBtnOnAction(ActionEvent event) {

    }

    @FXML
    void homeBtnOnAction(ActionEvent event) {

    }

    @FXML
    void itemBtnOnAction(ActionEvent event) {

    }

    @FXML
    void logoutBtnOnAction(ActionEvent event) {

    }

    @FXML
    void orderBtnOnAction(ActionEvent event) {

    }

    @FXML
    void paymentBtnOnAction(ActionEvent event) {

    }

    @FXML
    void supplierBtnOnAction(ActionEvent event) {

    }

}
