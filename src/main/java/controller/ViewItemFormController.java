package controller;

import dto.tm.ItemTM;
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
import dao.ItemModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ViewItemFormController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<ItemTM> tblItem;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colBrand;

    @FXML
    private TableColumn<?, ?> colModel;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    private void getAll() {
        try {
            List<ItemTM> itemTMS = ItemModel.getAll();
            ObservableList<ItemTM> list = FXCollections.observableArrayList();
            for (ItemTM itemTM :itemTMS){
                list.add(
                        new ItemTM(
                                itemTM.getItemId(),
                                itemTM.getBrand(),
                                itemTM.getModel(),
                                itemTM.getUnitPrice(),
                                itemTM.getQtyOnHand()
                        ));
            }
            tblItem.setItems(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {

        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("dao"));
        colType.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();
    }
    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/itemForm.fxml"));
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
