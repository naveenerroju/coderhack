package com.naveen.coderhack.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    private String userId;

    @NotEmpty(message = "username must not be null or empty")
    private String username;

    @NotNull(message = "score must not be null")
    @Min(value = 0, message = "Score cannot be less than 0")
    @Max(value = 100, message = "Score cannot be more than 100")
    private int score;

    @Size(min = 1, max = 3, message = "Array must contain between 1 and 3 elements")
    private List<String> badges;

}
