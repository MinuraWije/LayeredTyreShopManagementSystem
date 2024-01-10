package view.tdm;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class SupplierTM {
    private String supplierId;
    private String name;
    private String address;
    private int telNum;
    private String email;
}
