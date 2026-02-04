package sk.upjs.paz.rest.animal;

import sk.upjs.paz.animal.Sex;
import sk.upjs.paz.animal.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AnimalDto(
        Long id,
        String nickname,
        String species,
        Sex sex,
        LocalDate birthDay,
        LocalDateTime lastCheck,
        Status status,
        EnclosureRefDto enclosure
) { }
