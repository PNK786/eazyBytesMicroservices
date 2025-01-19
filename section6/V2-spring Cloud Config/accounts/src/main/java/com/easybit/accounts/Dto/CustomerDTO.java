package com.easybit.accounts.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Customer",
description = "schema to hold customer details")
public class CustomerDTO {
    @Schema(
            description = "Name of the customer",example = "john"
    )
    @NotEmpty(message = "Name cannot be null or Empty")
    @Size(min=5,max=30,message="length of the customer should be between 5 to 30")
    private String name;

    @Schema(
            description = "MOBILE NUMBER of the customer",example = "9XXXXXXXX0"
    )
    @NotEmpty(message = "Email cannot be null or Empty")
    @Email(message="Email address should be a valid value")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})",message="Mobile Number Must be 10 digits")
    private String mobileNumber;

    private AccountsDto accountsDto;
}
