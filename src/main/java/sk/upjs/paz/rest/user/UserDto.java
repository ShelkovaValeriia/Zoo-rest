package sk.upjs.paz.rest.user;

import sk.upjs.paz.user.Gender;
import sk.upjs.paz.user.Role;

import java.time.LocalDate;

public record UserDto(
        Long id,
        String firstName,
        String lastName,
        Gender gender,
        LocalDate birthDay,
        Role role,
        String email
) { }
