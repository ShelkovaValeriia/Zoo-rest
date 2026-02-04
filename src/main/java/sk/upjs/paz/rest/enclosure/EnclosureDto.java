package sk.upjs.paz.rest.enclosure;

import java.time.LocalDateTime;

public record EnclosureDto(
        Long id,
        String name,
        String zone,
        LocalDateTime lastMaintainance,
        Integer animalsCount
) { }
