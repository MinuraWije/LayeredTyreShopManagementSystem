package entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Item {
    private String itemId;
    private String brand;
    private String model;
    private Double unitPrice;
    private int qtyOnHand;
}
