package entity;

import dto.EmployeeDTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Employee extends EmployeeDTO {
    private String employeeId;
    private String name;
    private String address;
    private  int telNum;
    private String email;
    private String role;
}
