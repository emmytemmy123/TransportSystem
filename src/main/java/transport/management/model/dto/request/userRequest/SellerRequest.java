package transport.management.model.dto.request.userRequest;

import lombok.Data;
import transport.management.utills.MessageUtil;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.UUID;

@Data
public class SellerRequest {

        @NotNull(message = MessageUtil.INVALID_FIRSTNAME)
        @NotEmpty(message = MessageUtil.INVALID_FIRSTNAME)
        private String name;

        @NotNull(message = MessageUtil.INVALID_EMAIL)
        @NotEmpty(message = MessageUtil.INVALID_EMAIL)
        private String email;

//        @NotNull(message = INVALID_GENDER)
//        @NotEmpty(message = INVALID_GENDER)
        private String gender;

//        @NotNull(message = INVALID_COUNTRY)
//        @NotEmpty(message = INVALID_COUNTRY)
        private String country;

        @NotNull(message = MessageUtil.INVALID_CITY)
        @NotEmpty(message = MessageUtil.INVALID_CITY)
        private String city;

        @NotNull(message = MessageUtil.INVALID_ADDRESS)
        @NotEmpty(message = MessageUtil.INVALID_ADDRESS)
        private String address;

        @NotNull(message = MessageUtil.INVALID_PHONE)
        @NotEmpty(message = MessageUtil.INVALID_PHONE)
        private String phone;

        @NotNull(message = MessageUtil.INVALID_PHONE)
        @NotEmpty(message = MessageUtil.INVALID_PHONE)
        private String photo;

        @NotNull(message = MessageUtil.INVALID_DESIGNATION)
        @NotEmpty(message = MessageUtil.INVALID_DESIGNATION)
        private String designation;

        @NotNull(message = MessageUtil.INVALID_USERNAME)
        @NotEmpty(message = MessageUtil.INVALID_USERNAME)
        private String username;

        @NotNull(message = MessageUtil.INVALID_PASSWORD)
        @NotEmpty(message = MessageUtil.INVALID_PASSWORD)
        private String password;

        @NotNull(message = MessageUtil.INVALID_USERNAME)
        @NotEmpty(message = MessageUtil.INVALID_USERNAME)
        private String role;

        @NotNull(message = MessageUtil.INVALID_USERNAME)
//        @NotEmpty(message = INVALID_USERNAME)
        private UUID createdById;

}
