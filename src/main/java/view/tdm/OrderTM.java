package view.tdm;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class OrderTM {
    private String orderId;
    private String customerId;
    private LocalDate orderDate;
}
