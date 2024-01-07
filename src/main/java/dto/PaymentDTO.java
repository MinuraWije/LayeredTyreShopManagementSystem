package dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString


public class PaymentDTO {
    private String paymentId;
    private String orderId;
    private double amount;
    private LocalDate paymentDate;
    private String description;
}
