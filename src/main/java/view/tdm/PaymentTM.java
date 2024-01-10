package view.tdm;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString


public class PaymentTM {
    private String paymentId;
    private String orderId;
    private double amount;
    private LocalDate paymentDate;
    private String description;
}
