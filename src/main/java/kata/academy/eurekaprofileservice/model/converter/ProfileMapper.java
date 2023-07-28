package kata.academy.eurekaprofileservice.model.converter;

import kata.academy.eurekaprofileservice.model.dto.ProfilePersistRequestDto;
import kata.academy.eurekaprofileservice.model.dto.ProfileUpdateRequestDto;
import kata.academy.eurekaprofileservice.model.entity.Profile;

public final class ProfileMapper {

    private ProfileMapper() {
    }

    public static Profile toEntity(ProfilePersistRequestDto dto) {
        Profile profile = new Profile();
        profile.setFirstName(dto.firstName());
        profile.setLastName(dto.lastName());
        profile.setBirthdate(dto.birthdate());
        profile.setGender(dto.gender());
        return profile;
    }

    public static Profile toEntity(ProfileUpdateRequestDto dto) {
        Profile profile = new Profile();
        profile.setFirstName(dto.firstName());
        profile.setLastName(dto.lastName());
        profile.setBirthdate(dto.birthdate());
        profile.setGender(dto.gender());
        return profile;
    }
}
