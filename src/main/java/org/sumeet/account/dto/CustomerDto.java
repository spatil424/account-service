package org.sumeet.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customers",
        description ="Schema to hold Customer Information"
)
public class CustomerDto {

    @NotEmpty(message = "Customer Name can not be blank or empty")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
    @Schema(description = "Non empty customer name",example = "Sumeet Patil")
    private String name;

    @NotEmpty(message = "Customer email can not be blank or empty")
    @Email(message = "Provide a valid email id")
    @Schema(description = "Non empty customer email id",example = "test@gmail.com")
    private String email;

    @NotEmpty(message = "Mobile number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
    @Schema(description = "Non empty customer mobile number",example = "9999999999")
    private String mobileNumber;
    private AccountDto accountDto;
}
