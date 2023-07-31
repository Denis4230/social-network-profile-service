package kata.academy.eurekaprofileservice.rest.mapper;

import kata.academy.eurekaprofileservice.model.dto.ProfileDto;
import kata.academy.eurekaprofileservice.model.entity.Profile;

public class ProfileEntityToDto {
    public ProfileDto map(Profile profile) {
        return new ProfileDto(profile);
    }
}
