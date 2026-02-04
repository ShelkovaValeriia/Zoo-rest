package sk.upjs.paz.rest.animal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import sk.upjs.paz.animal.Sex;
import sk.upjs.paz.animal.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AnimalUpsertDto(
        String nickname,
        @NotBlank String species,
        @NotNull Sex sex,
        @NotNull LocalDate birthDay,
        LocalDateTime lastCheck,
        @NotNull Status status,
        @NotNull Long enclosureId
) { }
