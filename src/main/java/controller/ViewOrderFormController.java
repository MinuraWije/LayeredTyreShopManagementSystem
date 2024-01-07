package controller;

import dto.tm.OrderTM;
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
import dao.OrderModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ViewOrderFormController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<OrderTM> tblOrder;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colOrderDate;

    private void getAll() {
        try {
            List<OrderTM> orderTMList = OrderModel.getAll();
            ObservableList<OrderTM> list = FXCollections.observableArrayList();
            for (OrderTM orderTM :orderTMList){
                list.add(
                        new OrderTM(
                                orderTM.getOrderId(),
                                orderTM.getCustomerId(),
                                orderTM.getOrderDate()
                        ));
            }
            tblOrder.setItems(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {

        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/orderForm.fxml"));
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
