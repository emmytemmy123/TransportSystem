package logistic.management.model.dto.response.userResponse;

import logistic.management.model.dto.BaseDto;
import lombok.Data;

@Data
public class CustomerResponse extends BaseDto {

    private String name;
    private String email;
    private String phone;
    private String address;
    private String photo;
    private String gender;
    private String country;
    private String city;
    private String nin;
    private String username;
    private String roles;
    private String createdBy;

}
