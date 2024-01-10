package view.tdm;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class CustomerTM {
    private String customerId;
    private String name;
    private String address;
    private int telNum;
    private String email;
}
