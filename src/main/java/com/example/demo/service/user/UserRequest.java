package com.example.demo.service.user;


import com.example.demo.common.constants.Messages;
import com.example.demo.common.constants.Regex;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotBlank
    @Pattern(regexp = Regex.EmailRegex, message = Messages.User.EmailNotValid)
    private String email;
    @NotBlank
    @Length(min = 8, message = "Length must be greater than 8")
    private String password;
}
//todo tamam