package entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Supplier {
    private String supplierId;
    private String name;
    private String address;
    private int telNum;
    private String email;
}
