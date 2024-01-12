package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import bo.custom.ItemBO;
import bo.custom.OrderBO;
import bo.custom.PlaceOrderBO;
import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import dto.CustomerDTO;
import dto.ItemDTO;
import dto.PlaceOrderDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import view.tdm.CartTM;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class PlaceOrderFormController implements Initializable{

    @FXML
    private AnchorPane root;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblOrderDate;

    @FXML
    private ComboBox cmbCustomerId;

    @FXML
    private Label lblCustomerName;

    @FXML
    private ComboBox cmbItemId;

    @FXML
    private Label lblBrand;

    @FXML
    private Label lblModel;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private TableView<CartTM> tblCart;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colBrand;

    @FXML
    private TableColumn<?, ?> colModel;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private Label lblTotal;

    OrderBO orderBO = (OrderBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.ORDER);
    CustomerBO customerBO = (CustomerBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.CUSTOMER);
    private ObservableList<CartTM> obList = FXCollections.observableArrayList();
    PlaceOrderBO placeOrderBO= (PlaceOrderBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.PLACE_ORDER);
    ItemBO itemBO= (ItemBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.ITEM);

    @FXML
    void btnCartOnAction(ActionEvent event) {
        String itemId = (String) cmbItemId.getValue();
        String brand = lblBrand.getText();
        String model = lblModel.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        double tot = unitPrice * qty;
        Button btn = new Button("Remove");

        setRemoveBtnAction(btn);
        btn.setCursor(Cursor.HAND);


        if (!obList.isEmpty()) {
            for (int i = 0; i < tblCart.getItems().size(); i++) {
                if (colItemId.getCellData(i).equals(itemId)) {
                    int col_qty = (int) colQty.getCellData(i);
                    qty += col_qty;
                    tot = unitPrice * qty;

                    obList.get(i).setQty(qty);
                    obList.get(i).setTotal(tot);

                    calculateTotal();
                    tblCart.refresh();
                    return;
                }
            }
        }
        var cartTm = new CartTM(itemId, brand,model, qty, unitPrice, tot, btn);

        obList.add(cartTm);

        tblCart.setItems(obList);
        calculateTotal();
        txtQty.clear();
    }

    private void calculateTotal() {
        double total = 0;
        for (int i = 0; i < tblCart.getItems().size(); i++) {
            total += (double) colTotal.getCellData(i);
        }
        lblTotal.setText(String.valueOf(total));
    }

    private void setRemoveBtnAction(Button btn) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                int focusedIndex = tblCart.getSelectionModel().getSelectedIndex();

                obList.remove(focusedIndex);
                tblCart.refresh();
                calculateTotal();
            }
        });
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        String orderId = lblOrderId.getText();
        LocalDate pickupDate = LocalDate.parse(lblOrderDate.getText());
        double amount = Double.parseDouble(lblTotal.getText());
        String customerId = (String) cmbCustomerId.getValue();

        List<CartTM> cartTMList = new ArrayList<>();
        for (int i = 0; i < tblCart.getItems().size(); i++) {
            CartTM cartTm = obList.get(i);

            cartTMList.add(cartTm);
        }

        System.out.println("Place order form controller: " + cartTMList);
        var placeOrderDto = new PlaceOrderDTO(orderId, pickupDate,customerId, cartTMList);
        try {
            boolean isSuccess = placeOrderBO.placeOrder(placeOrderDto);
            if (isSuccess) {
                new Alert(Alert.AlertType.CONFIRMATION, "Order Success!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbCustomerIdOnAction(ActionEvent event) {
        String customerId = (String) cmbCustomerId.getValue();

        try {
            CustomerDTO customerDto = customerBO.searchCustomer(customerId);
            lblCustomerName.setText(customerDto.getName());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCustomerIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            ArrayList<CustomerDTO> idList = customerBO.getAllCustomer();

            for (CustomerDTO dto : idList) {
                obList.add(dto.getCustomerId());
            }

            cmbCustomerId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateNextOrderId() {
        try {
            String orderId = orderBO.generateNextOrderId();
            lblOrderId.setText(orderId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setDate() {
        lblOrderDate.setText(String.valueOf(LocalDate.now()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDate();
        generateNextOrderId();
        loadCustomerIds();
        loadItemCodes();
        setCellValueFactory();
    }
    @FXML
    void cmbItemIdOnAction(ActionEvent event) {
        String itemId = (String) cmbItemId.getValue();

        txtQty.requestFocus();
        try {
            ItemDTO dto = itemBO.searchItem(itemId);
            lblBrand.setText(dto.getBrand());
            lblModel.setText(dto.getModel());
            lblUnitPrice.setText(String.valueOf(dto.getUnitPrice()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadItemCodes() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            ArrayList<ItemDTO> itemDtos = itemBO.getAllItem();

            for (ItemDTO dto : itemDtos) {
                obList.add(dto.getItemId());
            }
            cmbItemId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("dao"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    @FXML
    void btnPrintOnAction(ActionEvent event) throws JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/Report/Order.jrxml");

        JasperDesign load = JRXmlLoader.load(resourceAsStream);

        JasperReport jasperReport = JasperCompileManager.compileReport(load);

        JasperPrint jasperPrint = JasperFillManager.fillReport(
                jasperReport,
                null,
                DbConnection.getInstance().getConnection()
        );

        JasperViewer.viewReport(jasperPrint, false);
    }

    @FXML
    void customerBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/CustomerForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
    }

    @FXML
    void employeeBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/EmployeeForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
    }

    @FXML
    void homeBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
    }

    @FXML
    void itemBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/ItemForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
    }

    @FXML
    void logoutBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
    }

    @FXML
    void orderBtnOnAction(ActionEvent event) {

    }

    @FXML
    void paymentBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/PaymentForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
    }

    @FXML
    void supplierBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/SupplierForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
    }

}
