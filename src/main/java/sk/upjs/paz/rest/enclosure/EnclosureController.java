package sk.upjs.paz.rest.enclosure;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.web.bind.annotation.*;
import sk.upjs.paz.enclosure.EnclosureDao;
import sk.upjs.paz.exceptions.NotFoundException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/enclosures")
public class EnclosureController {

    private final EnclosureDao enclosureDao;
    private final JdbcOperations jdbc;

    public EnclosureController(EnclosureDao enclosureDao, JdbcOperations jdbc) {
        this.enclosureDao = enclosureDao;
        this.jdbc = jdbc;
    }

    @GetMapping
    public List<EnclosureDto> getAll() {
        return enclosureDao.getAll().stream()
                .map(EnclosureMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public EnclosureDto getById(@PathVariable long id) {
        return EnclosureMapper.toDto(enclosureDao.getById(id));
    }

    @PostMapping
    public ResponseEntity<EnclosureDto> create(@Valid @RequestBody EnclosureUpsertDto body) {
        var created = enclosureDao.create(EnclosureMapper.fromUpsert(null, body));
        var dto = EnclosureMapper.toDto(created);
        return ResponseEntity.created(URI.create("/api/enclosures/" + dto.id())).body(dto);
    }

    @PutMapping("/{id}")
    public EnclosureDto update(@PathVariable long id, @Valid @RequestBody EnclosureUpsertDto body) {
        enclosureDao.getById(id);
        var updated = enclosureDao.update(EnclosureMapper.fromUpsert(id, body));
        return EnclosureMapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        int rows = jdbc.update("DELETE FROM enclosure WHERE id = ?", id);
        if (rows == 0) {
            throw new NotFoundException("Enclosure with id " + id + " not found.");
        }
        return ResponseEntity.noContent().build();
    }
}
