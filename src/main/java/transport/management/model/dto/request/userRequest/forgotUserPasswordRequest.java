package transport.management.model.dto.request.userRequest;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import transport.management.utills.MessageUtil;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class forgotUserPasswordRequest {

    @NotNull(message = MessageUtil.INVALID_PASSWORD)
    @NotEmpty(message = MessageUtil.INVALID_PASSWORD)
    private String email;

}
