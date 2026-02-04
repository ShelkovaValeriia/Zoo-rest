package sk.upjs.paz.rest.enclosure;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public record EnclosureUpsertDto(
        @NotBlank String name,
        @NotBlank String zone,
        LocalDateTime lastMaintainance
) { }
