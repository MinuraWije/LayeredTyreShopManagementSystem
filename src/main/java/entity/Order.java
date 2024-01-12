package entity;

import java.time.LocalDate;

import dto.OrderDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Order extends OrderDTO {
    private String orderId;
    private String customerId;
    private LocalDate orderDate;
}
