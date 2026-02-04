package sk.upjs.paz.rest.enclosure;

import sk.upjs.paz.enclosure.Enclosure;

public class EnclosureMapper {

    public static EnclosureDto toDto(Enclosure e) {
        return new EnclosureDto(
                e.getId(),
                e.getName(),
                e.getZone(),
                e.getLastMaintainance(),
                e.getAnimalsCount()
        );
    }

    public static Enclosure fromUpsert(Long id, EnclosureUpsertDto dto) {
        Enclosure e = new Enclosure();
        e.setId(id);
        e.setName(dto.name());
        e.setZone(dto.zone());
        e.setLastMaintainance(dto.lastMaintainance());
        e.setAnimalsCount(null);
        return e;
    }
}
