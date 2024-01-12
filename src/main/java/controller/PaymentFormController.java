package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import bo.custom.OrderBO;
import bo.custom.PaymentBO;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dto.OrderDTO;
import dto.PaymentDTO;
import view.tdm.OrderTM;
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
import dao.custom.impl.OrderDAOImpl;
import dao.custom.impl.PaymentDAOImpl;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import java.util.ArrayList;


public class PaymentFormController {

    public ComboBox cmbOrderId;

    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txtPaymentId;

    @FXML
    private JFXTextField txtAmount;

    @FXML
    private JFXDatePicker paymentDate;

    @FXML
    private JFXTextField txtDescription;

    private OrderDAOImpl orderDAOImpl = new OrderDAOImpl();
    OrderBO orderBO = (OrderBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.ORDER);
    PaymentBO paymentBO = (PaymentBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.PAYMENT);
    ObservableList<PaymentDTO> observableList = FXCollections.observableArrayList();

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String paymentId = txtPaymentId.getText();

        try {
            boolean isRemoved = paymentBO.deletePayment(paymentId);

            if (isRemoved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted successfully").show();
                txtPaymentId.setText("");
                cmbOrderId.setValue("");
                txtAmount.setText("");
                paymentDate.setValue(LocalDate.parse(""));
                txtDescription.setText("");
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
        boolean isValidated = validatePayment();
        if(isValidated){
            String paymentId = txtPaymentId.getText();
            String orderId = (String) cmbOrderId.getValue();
            Double amount = Double.parseDouble(txtAmount.getText());
            LocalDate date = paymentDate.getValue();
            String description = txtDescription.getText();

            try {
                boolean isSaved = paymentBO.savePayment(new PaymentDTO(paymentId, orderId,amount, date, description));

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved  !!!").show();
                    txtPaymentId.setText("");
                    cmbOrderId.setValue("");
                    txtAmount.setText("");
                    paymentDate.setValue(LocalDate.parse(""));
                    txtDescription.setText("");
                    observableList.clear();

                } else {
                    new Alert(Alert.AlertType.ERROR, "Not saved  !!!").show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean validatePayment() {
        String paymentId = txtPaymentId.getText();
        boolean matches = Pattern.matches("[P][0-9]{3,}",paymentId);

        if(!matches){
            new Alert(Alert.AlertType.ERROR, "Invalid payment id.").show();
            return false;
        }
        return true;
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String paymentId = txtPaymentId.getText();
        String orderId = (String) cmbOrderId.getValue();
        Double amount = Double.parseDouble(txtAmount.getText());
        LocalDate date = paymentDate.getValue();
        String description = txtDescription.getText();

        boolean isUpdated = false;
        try {
            isUpdated = paymentBO.updatePayment(new PaymentDTO(paymentId, orderId, amount,date,description));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated successfully").show();
                txtPaymentId.setText("");
                cmbOrderId.setValue("");
                txtAmount.setText("");
                paymentDate.setValue(LocalDate.parse(""));
                txtDescription.setText("");
                observableList.clear();

            } else {
                new Alert(Alert.AlertType.ERROR, "Update failed").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void txtPaymentIdSearchOnAction(ActionEvent event) {
        String paymentId = txtPaymentId.getText();

        try {
            PaymentDTO paymentDTO= paymentBO.searchPayment(paymentId);

            if (paymentDTO != null) {
                txtPaymentId.setText(paymentDTO.getPaymentId());
                cmbOrderId.setValue(paymentDTO.getOrderId());
                txtAmount.setText(String.valueOf(paymentDTO.getAmount()));
                paymentDate.setValue(paymentDTO.getPaymentDate());
                txtDescription.setText(paymentDTO.getDescription());
            }else {
                new Alert(Alert.AlertType.ERROR,"Invalid ID").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void btnPrintOnAction(ActionEvent event) {
        String id = txtPaymentId.getText();

        try {
            PaymentDTO dto = paymentBO.searchPayment(id);
            if(dto!=null){
                try{
                    viewPaymentReport(dto);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void viewPaymentReport(PaymentDTO dto) throws JRException {
        HashMap hashMap = new HashMap();
        hashMap.put("paymentid",dto.getPaymentId());
        hashMap.put("orderid",dto.getOrderId());
        hashMap.put("amount",dto.getAmount());
        hashMap.put("paymentdate",dto.getPaymentDate());
        hashMap.put("description",dto.getDescription());


        InputStream resourceAsStream =  getClass().getResourceAsStream("/Report/Payment_report.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport= JasperCompileManager.compileReport(load);

        JasperPrint jasperPrint = JasperFillManager.fillReport(
                jasperReport,
                hashMap,
                new JREmptyDataSource()
        );

        JasperViewer.viewReport(jasperPrint,false);
    }

    @FXML
    void cmbOrderIdOnAction(ActionEvent event) {
        String orderId = (String) cmbOrderId.getValue();

        try {
            OrderDTO orderDTO = orderBO.searchOrder(orderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadOrderId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            ArrayList<OrderDTO> orderTMS = orderBO.getAllOrder();

            for (OrderDTO dto : orderTMS) {
                obList.add(dto.getOrderId());
            }
            cmbOrderId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize() {
        loadOrderId();
    }

    @FXML
    void btnViewOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/viewPaymentForm.fxml"));
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
    void paymentBtnOnAction(ActionEvent event) {

    }

    @FXML
    void supplierBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/supplierForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
    }

}
