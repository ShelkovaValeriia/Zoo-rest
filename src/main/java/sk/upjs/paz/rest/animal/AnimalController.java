package sk.upjs.paz.rest.animal;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.web.bind.annotation.*;
import sk.upjs.paz.animal.AnimalDao;
import sk.upjs.paz.animal.Status;
import sk.upjs.paz.exceptions.NotFoundException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    private final AnimalDao animalDao;
    private final JdbcOperations jdbc;

    public AnimalController(AnimalDao animalDao, JdbcOperations jdbc) {
        this.animalDao = animalDao;
        this.jdbc = jdbc;
    }

    @GetMapping
    public List<AnimalDto> getAll() {
        return animalDao.getAll().stream().map(AnimalMapper::toDto).toList();
    }

    @GetMapping("/sorted")
    public List<AnimalDto> getAllSortedByZoneSpecies() {
        return animalDao.getAllSortedByZoneSpecies().stream().map(AnimalMapper::toDto).toList();
    }

    @GetMapping("/status/{status}")
    public List<AnimalDto> getByStatus(@PathVariable Status status) {
        return animalDao.getByStatus(status).stream().map(AnimalMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public AnimalDto getById(@PathVariable long id) {
        return AnimalMapper.toDto(animalDao.getById(id));
    }

    @PostMapping
    public ResponseEntity<AnimalDto> create(@Valid @RequestBody AnimalUpsertDto body) {
        var created = animalDao.create(AnimalMapper.fromUpsert(null, body));
        var dto = AnimalMapper.toDto(created);
        return ResponseEntity.created(URI.create("/api/animals/" + dto.id())).body(dto);
    }

    @PutMapping("/{id}")
    public AnimalDto update(@PathVariable long id, @Valid @RequestBody AnimalUpsertDto body) {
        animalDao.getById(id);
        var updated = animalDao.update(AnimalMapper.fromUpsert(id, body));
        return AnimalMapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        int rows = jdbc.update("DELETE FROM animal WHERE id = ?", id);
        if (rows == 0) {
            throw new NotFoundException("Animal with id " + id + " not found.");
        }
        return ResponseEntity.noContent().build();
    }
}
