package com.naveen.coderhack.entity;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Objects;

@Document(collection = "users") // Maps the class to the MongoDB "users" collection
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {

    @Id
    @Field("_id")  // MongoDB default field for the unique identifier
    private String userId;  // Unique user ID

    @NotEmpty(message = "Username must not be null or empty")
    @Field("username")
    private String username;  // Username of the user

    @NotNull(message = "Score must not be null")
    @Min(value = 0, message = "Score cannot be less than 0")
    @Max(value = 100, message = "Score cannot be more than 100")
    @Field("score")
    private int score;  // User score between 0 and 100

    @Size(min = 1, max = 3, message = "Array must contain between 1 and 3 elements")
    @Field("badges")
    private List<String> badges;  // List of user badges (1 to 3 elements)

    // equals and hashCode methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return score == that.score &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(username, that.username) &&
                Objects.equals(badges, that.badges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, score, badges);
    }
}
