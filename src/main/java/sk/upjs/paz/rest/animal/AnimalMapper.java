package sk.upjs.paz.rest.animal;

import sk.upjs.paz.animal.Animal;
import sk.upjs.paz.enclosure.Enclosure;

public class AnimalMapper {

    public static AnimalDto toDto(Animal a) {
        EnclosureRefDto enclosure = null;
        if (a.getEnclosure() != null) {
            var e = a.getEnclosure();
            enclosure = new EnclosureRefDto(e.getId(), e.getName(), e.getZone());
        }

        return new AnimalDto(
                a.getId(),
                a.getNickname(),
                a.getSpecies(),
                a.getSex(),
                a.getBirthDay(),
                a.getLastCheck(),
                a.getStatus(),
                enclosure
        );
    }

    public static Animal fromUpsert(Long id, AnimalUpsertDto dto) {
        Animal a = new Animal();
        a.setId(id);
        a.setNickname(dto.nickname());
        a.setSpecies(dto.species());
        a.setSex(dto.sex());
        a.setBirthDay(dto.birthDay());
        a.setLastCheck(dto.lastCheck());
        a.setStatus(dto.status());

        Enclosure e = new Enclosure();
        e.setId(dto.enclosureId());
        a.setEnclosure(e);

        return a;
    }
}
