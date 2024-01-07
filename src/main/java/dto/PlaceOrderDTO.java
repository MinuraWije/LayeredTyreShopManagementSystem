package dto;

import dto.tm.CartTM;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class PlaceOrderDTO {
    private String orderId;
    private LocalDate pickupDate;
    private String customerId;
    private List<CartTM> cartTmList = new ArrayList<>();
}
