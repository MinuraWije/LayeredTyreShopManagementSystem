package entity;

import lombok.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Payment {
    private String paymentId;
    private String orderId;
    private double amount;
    private LocalDate paymentDate;
    private String description;
}
