package view.tdm;

import javafx.scene.control.Button;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class CartTM {
    private String itemId;
    private String brand;
    private String model;
    private int qty;
    private double unitPrice;
    private double total;
    private Button btn;
}
