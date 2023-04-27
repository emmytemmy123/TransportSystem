package fcmb.com.good.model.dto.response.assetsResponse;

import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

@Data
public class AssetsCategoryResponse extends BaseDto {

    private String name;
    private String description;
    private Long accountNo;

}
