package dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class OrderDTO {
    private String orderId;
    private String customerId;
    private LocalDate orderDate;
}
