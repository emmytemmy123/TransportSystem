package fcmb.com.good.model.dto.request.transactionRequest;

import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.UUID;

import static fcmb.com.good.utills.MessageUtil.INVALID_NAME;

@Data
public class ExpenseRequest {

      @NotNull(message = INVALID_NAME)
      @NotEmpty(message = INVALID_NAME)
      private String category;

      @NotNull(message = INVALID_NAME)
      @NotEmpty(message = INVALID_NAME)
      private String description;

      @NotNull(message = INVALID_NAME)
      @NotEmpty(message = INVALID_NAME)
      private String name;

      @NotNull(message = INVALID_NAME)
      @Min(value = 50, message = INVALID_NAME)
      private Double price;

      @NotNull(message = INVALID_NAME)
      @Min(value = 1, message = INVALID_NAME)
      private Integer quantity;

      @NotNull(message = INVALID_NAME)
      private UUID createdById;

      @NotNull(message = INVALID_NAME)
      private UUID currentEmployeeId;

      @NotNull(message = INVALID_NAME)
      private UUID currentExpenseCategoryId;



}
