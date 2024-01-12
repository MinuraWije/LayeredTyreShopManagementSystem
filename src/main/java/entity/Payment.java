package entity;

import dto.PaymentDTO;
import lombok.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Payment extends PaymentDTO {
    private String paymentId;
    private String orderId;
    private double amount;
    private LocalDate paymentDate;
    private String description;
}
