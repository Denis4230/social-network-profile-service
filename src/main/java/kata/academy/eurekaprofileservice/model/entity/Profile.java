package kata.academy.eurekaprofileservice.model.entity;

import kata.academy.eurekaprofileservice.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "profiles")
public class Profile {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true, nullable = false)
    private Long userId;

    private String firstName;

    private String lastName;

    private LocalDate birthdate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String avatarPicUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(id, profile.id) && userId.equals(profile.userId) && Objects.equals(firstName, profile.firstName) && Objects.equals(lastName, profile.lastName) && Objects.equals(birthdate, profile.birthdate) && gender == profile.gender && Objects.equals(avatarPicUrl, profile.avatarPicUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, firstName, lastName, birthdate, gender, avatarPicUrl);
    }
}
