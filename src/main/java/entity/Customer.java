package entity;

import dto.CustomerDTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Customer extends CustomerDTO {
    private String customerId;
    private String name;
    private String address;
    private int telNum;
    private String email;
}
