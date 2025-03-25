package com.easybit.Accounts.controller;


import com.easybit.Accounts.Dto.CustomerDTO;
import com.easybit.Accounts.Dto.ErrorResponseDto;
import com.easybit.Accounts.Dto.ResponseDto;
import com.easybit.Accounts.constants.AccountsContants;
import com.easybit.Accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
@Tag(
        name = "CRUD REST Api Accounts Eazy Bank",
        description ="CRUD Rest Api for CREATE UPDATE DELETE"
)
public class AccountController {

    private IAccountService iAccountService;

    @Operation(
            summary = "Create Account REST Api",
            description = "create Account Rest Api"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HttpStatus Created"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDTO customerdto) {
        iAccountService.createAccount(customerdto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountsContants.STATUS_201, AccountsContants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Account Details REST Api",
            description = "REST API to fetch Customer Details based on mobile Number"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HttpStatus Updated"
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDTO> fetchAccountDetails(@RequestParam
                                                            @Pattern(regexp = "(^$|[0-9]{10})",
                                                                    message = "Mobile Number Must be 10 digits")
                                                               String mobileNumber) {

        CustomerDTO customerDTO = iAccountService.fetchDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
    }

    @Operation(
            summary = "Update Account REST Api",
            description = "create Account Rest Api"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HttpStatus Created"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Exception Failed"

            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HttpStatus Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDTO customerDTO) {
        boolean isUpdated = iAccountService.updateAccount(customerDTO);

        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).
                    body(new ResponseDto(AccountsContants.STATUS_200, AccountsContants.MESSAGE_200));

        } else {
            return ResponseEntity.
                    status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsContants.STATUS_417, AccountsContants.MESSAGE_417_UPDATE));
        }
    }
    @Operation(
            summary = "Delete Customer Account REST Api",
            description = "Rest Api to delete Customer Account details based on a mobile Number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HttpStatus Created"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Exception Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number Must be 10 digits") String mobileNumber) {
        boolean isDeleted = iAccountService.deleteAccount(mobileNumber);

        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsContants.STATUS_200, AccountsContants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsContants.STATUS_417, AccountsContants.MESSAGE_417_DELETE));
        }
    }

}
