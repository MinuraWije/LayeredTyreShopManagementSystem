package dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class CustomerDTO {
    private String customerId;
    private String name;
    private String address;
    private int telNum;
    private String email;
}
