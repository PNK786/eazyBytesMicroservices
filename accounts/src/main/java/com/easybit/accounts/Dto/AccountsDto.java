package com.easybit.accounts.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "Accounts",description = "Schema to hold Account Details")
public class AccountsDto {

    @NotEmpty(message = "Account Number cannot be null or Empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message="Mobile Number Must be 10 digits")
    @Schema(description = "Account Number of Bank",example = "30xxxxxxxx6")
    private Long accountNumber;

    @Schema(
            description = "Type of the account",example = "Savings"
    )
    @NotEmpty(message = "Account Type cannot be null or Empty")
    private String accountType;

    @Schema(
            description = "Branch Address")
    @NotEmpty(message = "branch Address cannot be null or Empty")
    private String branchAddress;
}
