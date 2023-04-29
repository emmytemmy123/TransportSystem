package transport.management.model.dto.request.othersRequest;

import lombok.Data;
import transport.management.utills.MessageUtil;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class DocumentRequest  {

       @NotNull(message = MessageUtil.INVALID_NAME)
       @NotEmpty(message = MessageUtil.INVALID_NAME)
       private String Type;

       @NotNull(message = MessageUtil.INVALID_NAME)
       @NotEmpty(message = MessageUtil.INVALID_NAME)
       private String Name;

       @NotNull(message = MessageUtil.INVALID_NAME)
       @NotEmpty(message = MessageUtil.INVALID_NAME)
       private String Size;

       @NotNull(message = MessageUtil.INVALID_NAME)
       @NotEmpty(message = MessageUtil.INVALID_NAME)
       private String description;

       @NotNull(message = MessageUtil.INVALID_NAME)
       @NotEmpty(message = MessageUtil.INVALID_NAME)
       private String filePath;



}