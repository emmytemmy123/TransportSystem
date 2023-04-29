package transport.management.model.dto.response.userResponse;

import transport.management.model.dto.BaseDto;
import lombok.Data;

import java.util.UUID;

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
    private boolean active;
    private String createdBy;

}
