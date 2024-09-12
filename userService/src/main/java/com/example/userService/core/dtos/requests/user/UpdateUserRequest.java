package com.example.userService.core.dtos.requests.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    @Size(max = 50,message = "Maksimum 50 karakterlik veri girişi yapınız.")
    private String name;

    @Email(message = "Mail formatında giriniz.")
    @Size(max = 50,message = "Maksimum 50 karakterlik veri girişi yapınız.")
    private String email;

    @Size(max = 50,message = "Maksimum 50 karakterlik veri girişi yapınız.")
    private String password;
}
