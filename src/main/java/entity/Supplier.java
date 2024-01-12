package entity;

import dto.SupplierDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Supplier extends SupplierDTO {
    private String supplierId;
    private String name;
    private String address;
    private int telNum;
    private String email;
}
