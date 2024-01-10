package view.tdm;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class ItemTM {
    private String itemId;
    private String brand;
    private String model;
    private Double unitPrice;
    private int qtyOnHand;
}
