package com.munsal.java21demo.domain.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.munsal.java21demo.domain.jsonView.ViewRole;
import jakarta.validation.constraints.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CustomerDto {
    @JsonView({ViewRole.DeleteRequest.class, ViewRole.ViewRequest.class })
    private Long id;

    @NotBlank
    @Size(max = 20)
    @JsonView({ViewRole.ViewRequest.class, ViewRole.LoginRequest.class, ViewRole.AddRequest.class, ViewRole.UpsertRequest.class})
    private String username;

    @NotBlank
    @Size(max = 120)
    @JsonView({ViewRole.AddRequest.class, ViewRole.LoginRequest.class, ViewRole.UpsertRequest.class})
    private String password;

    @NotEmpty(message = "Name field cannot be empty")
    @JsonView({ViewRole.ViewRequest.class, ViewRole.AddRequest.class, ViewRole.UpsertRequest.class})
    private String name;

    @NotEmpty(message = "Surname field cannot be empty")
    @JsonView({ViewRole.ViewRequest.class, ViewRole.AddRequest.class, ViewRole.UpsertRequest.class})
    private String surname;

    @NotEmpty(message = "Email cannot be empty")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE, message = "E-mail address is not valid.")
    @JsonView({ViewRole.ViewRequest.class, ViewRole.AddRequest.class, ViewRole.UpsertRequest.class})
    private String email;

    @NotEmpty(message = "Address cannot be empty")
    @JsonView({ViewRole.ViewRequest.class, ViewRole.AddRequest.class, ViewRole.UpsertRequest.class})
    private String address;

    @NotEmpty(message = "Phone cannot be empty")
    @Pattern(regexp = "(\\+90|0)[0-9]{10}", message = "Phone number is not valid.")
    @JsonView({ViewRole.ViewRequest.class, ViewRole.AddRequest.class, ViewRole.UpsertRequest.class})
    private String phone;
}
