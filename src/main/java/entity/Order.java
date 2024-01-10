package entity;

import java.time.LocalDate;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Order {
    private String orderId;
    private String customerId;
    private LocalDate orderDate;
}
