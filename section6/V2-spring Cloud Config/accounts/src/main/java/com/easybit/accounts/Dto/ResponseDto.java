package com.easybit.accounts.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
        name = "Response",
        description = "Schema to hold successful Response"
)
public class ResponseDto {

    @Schema(
            description = "Status code in response"
    )
    private String  statusCode;

    @Schema(
            description = "Status message in response"
    )
    private String statusMsg;

}
