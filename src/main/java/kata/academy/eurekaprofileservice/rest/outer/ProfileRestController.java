package kata.academy.eurekaprofileservice.rest.outer;

import kata.academy.eurekaprofileservice.api.Response;
import kata.academy.eurekaprofileservice.model.converter.ProfileMapper;
import kata.academy.eurekaprofileservice.model.dto.ProfilePersistRequestDto;
import kata.academy.eurekaprofileservice.model.dto.ProfileUpdateRequestDto;
import kata.academy.eurekaprofileservice.model.entity.Profile;
import kata.academy.eurekaprofileservice.service.ProfileService;
import kata.academy.eurekaprofileservice.util.ApiValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileRestController {

    private final ProfileService profileService;

    @PostMapping
    public Response<Profile> addProfile(@RequestBody ProfilePersistRequestDto dto,
                                        @RequestParam @Positive Long userId) {
        Profile profile = ProfileMapper.toEntity(dto);
        profile.setUserId(userId);
        return Response.ok(profileService.addProfile(profile));
    }

    @PutMapping("/{profileId}")
    public Response<Profile> updateProfile(@RequestBody ProfileUpdateRequestDto dto,
                                           @PathVariable @Positive Long profileId,
                                           @RequestParam @Positive Long userId) {
        ApiValidationUtil.requireTrue(profileService.existsByIdAndUserId(profileId, userId), String.format("Юзер с userId %d не имеет профиля с profileId %d в базе данных", userId, profileId));
        Profile profile = ProfileMapper.toEntity(dto);
        profile.setId(profileId);
        profile.setUserId(userId);
        return Response.ok(profileService.updateProfile(profile));
    }

    @DeleteMapping("/{profileId}")
    public Response<Void> deleteProfile(@PathVariable @Positive Long profileId,
                                        @RequestParam @Positive Long userId) {
        ApiValidationUtil.requireTrue(profileService.existsByIdAndUserId(profileId, userId), String.format("Юзер с userId %d не имеет профиля с profileId %d в базе данных", userId, profileId));
        profileService.deleteById(profileId);
        return Response.ok();
    }
}
