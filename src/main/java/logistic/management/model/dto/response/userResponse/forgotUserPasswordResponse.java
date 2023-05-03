package logistic.management.model.dto.response.userResponse;

import logistic.management.model.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class forgotUserPasswordResponse extends BaseDto {

    private String email;

}