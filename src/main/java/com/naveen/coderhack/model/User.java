package com.naveen.coderhack.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

/**
 * Represents a coder in the platform with a userId, username, score and badges.
 * The User class implements the {@link Comparable} interface to enable
 * sorting of users based on their score in ascending order.
 * The class also overrides the {@link Object#equals(Object)} and {@link Object#hashCode()}
 * methods to ensure proper behavior in collections that rely on hashing (e.g., {@link java.util.HashSet}, {@link java.util.HashMap}).
 *
 * <p>
 * Example usage:
 * <pre>
 *     User user1 = new User("Alice12", "Alice", 85, "Code Master");
 *     User user2 = new User("NightCoder", "Bob", 40, "Code Champ");
 *     List<User> users = Arrays.asList(user1, user2);
 *     Collections.sort(users); // Sorts users by score
 * </pre>
 * </p>
 *
 * @author Naveen Kumar
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Comparable<User> {

    @NotEmpty(message = "userId must not be null or empty")
    private String userId;

    @NotEmpty(message = "username must not be null or empty")
    private String username;

    @NotNull(message = "score must not be null")
    @Min(value = 0, message = "Score cannot be less than 0")
    @Max(value = 100, message = "Score cannot be more than 100")
    private int score;

    @Size(min = 1, max = 3, message = "Array must contain between 1 and 3 elements")
    private List<String> badges;

    @Override
    public int compareTo(User user) {
        return Integer.compare(this.score, user.score);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Check if it's the same object reference
        if (obj == null || getClass() != obj.getClass()) return false; // Null or different class

        User other = (User) obj; // Cast obj to User
        return score == other.score && Objects.equals(userId, other.userId); // Compare score and name
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username); // Hash code is based on userId and username
    }

}
