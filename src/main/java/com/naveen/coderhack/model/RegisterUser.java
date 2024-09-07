package com.naveen.coderhack.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class RegisterUser {
    @NotEmpty
    private String userId;
    @NotEmpty
    private String username;
}
