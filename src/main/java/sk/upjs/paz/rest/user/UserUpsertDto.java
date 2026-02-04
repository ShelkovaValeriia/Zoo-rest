package sk.upjs.paz.rest.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import sk.upjs.paz.user.Gender;
import sk.upjs.paz.user.Role;

import java.time.LocalDate;

public record UserUpsertDto(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotNull Gender gender,
        @NotNull LocalDate birthDay,
        @NotNull Role role,
        @Email @NotBlank String email,
        @NotBlank String password
) { }
