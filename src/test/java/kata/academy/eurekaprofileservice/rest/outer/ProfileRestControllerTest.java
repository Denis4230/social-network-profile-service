package kata.academy.eurekaprofileservice.rest.outer;

import kata.academy.eurekaprofileservice.SpringSimpleContextTest;
import kata.academy.eurekaprofileservice.model.dto.ProfilePersistRequestDto;
import kata.academy.eurekaprofileservice.model.entity.Profile;
import kata.academy.eurekaprofileservice.model.enums.Gender;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class ProfileRestControllerTest extends SpringSimpleContextTest {


    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/scripts/outer/ProfileRestController/After.sql")
    void addProfile() throws Exception {

        Long userId = 1L;

        LocalDate birthdate = LocalDate.of(2000, 12, 11);
        ProfilePersistRequestDto dto = new ProfilePersistRequestDto("first", "last", birthdate, Gender.MALE);

        mockMvc.perform(post("/api/v1/profiles/")
                .param("userId", userId.toString())
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.userId", Is.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.firstName", Is.is("first")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.lastName", Is.is("last")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.birthdate", Is.is(birthdate.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.gender", Is.is(Gender.MALE.name())));

        Profile profile = entityManager.find(Profile.class, userId);
        assertNotNull(profile);
        assertEquals("first", profile.getFirstName());
        assertEquals(userId, profile.getUserId());
        assertEquals("last", profile.getLastName());
        assertEquals(birthdate, profile.getBirthdate());
        assertEquals(Gender.MALE, profile.getGender());
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = "/scripts/outer/ProfileRestController/Before.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/scripts/outer/ProfileRestController/After.sql")
    void updateProfile() throws Exception{

        Long userId = 1L;
        Long profileId = 1L;

        LocalDate birthdate = LocalDate.of(2020, 10, 12);
        ProfilePersistRequestDto dto = new ProfilePersistRequestDto("newFirst", "newLast", birthdate, Gender.FEMALE);

        mockMvc.perform(put("/api/v1/profiles/{profileId}", profileId)
                .param("profileId", profileId.toString())
                .param("userId", userId.toString())
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(200)));


        Profile newProfile = entityManager.find(Profile.class, userId);
        assertNotNull(newProfile);
        assertEquals("newFirst", newProfile.getFirstName());
        assertEquals(userId, newProfile.getUserId());
        assertEquals("newLast", newProfile.getLastName());
        assertEquals(birthdate, newProfile.getBirthdate());
        assertEquals(Gender.FEMALE, newProfile.getGender());
        System.out.println(newProfile.getGender());

    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, value = "/scripts/outer/ProfileRestController/After.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, value = "/scripts/outer/ProfileRestController/Before.sql")
    void deleteProfile() throws Exception {

        Long userId = 1L;
        Long profileId = 1L;

        mockMvc.perform(delete("/api/v1/profiles/{profileId}", profileId)
                        .param("profileId", profileId.toString())
                        .param("userId", userId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(200)));

        Long userId2 = 2L;
        Long profileId2 = 2L;

        mockMvc.perform(delete("/api/v1/profiles/{profileId}", profileId2)
                        .param("profileId", profileId2.toString())
                        .param("userId", userId2.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is(400)));

    }
}






















