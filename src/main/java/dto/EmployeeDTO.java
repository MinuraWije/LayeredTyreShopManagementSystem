package dto;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class EmployeeDTO {
    private String employeeId;
    private String name;
    private String address;
    private  int telNum;
    private String email;
    private String role;
}
