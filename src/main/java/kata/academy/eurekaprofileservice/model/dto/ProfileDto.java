package kata.academy.eurekaprofileservice.model.dto;

import kata.academy.eurekaprofileservice.model.entity.Profile;
import kata.academy.eurekaprofileservice.model.enums.Gender;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

public class ProfileDto {

    private String firstName;

    private String lastName;

    private LocalDate birthdate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String avatarPicUrl;

    public ProfileDto(String firstName, String lastName, LocalDate birthdate, Gender gender, String avatarPicUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.gender = gender;
        this.avatarPicUrl = avatarPicUrl;
    }

    public ProfileDto(Profile profile) {
        firstName = profile.getFirstName();
        lastName = profile.getLastName();
        birthdate = profile.getBirthdate();
        gender = profile.getGender();
        avatarPicUrl = profile.getAvatarPicUrl();
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAvatarPicUrl() {
        return avatarPicUrl;
    }

    public void setAvatarPicUrl(String avatarPicUrl) {
        this.avatarPicUrl = avatarPicUrl;
    }
}
