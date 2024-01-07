package dto;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class SupplierDTO {
    private String supplierId;
    private String name;
    private String address;
    private int telNum;
    private String email;
}
