package entity;

import dto.ItemDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Item extends ItemDTO {
    private String itemId;
    private String brand;
    private String model;
    private Double unitPrice;
    private int qtyOnHand;
}
