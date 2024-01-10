package entity;

import view.tdm.CartTM;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class PlaceOrder {
    private String orderId;
    private LocalDate pickupDate;
    private String customerId;
    private List<CartTM> cartTmList = new ArrayList<>();
}
