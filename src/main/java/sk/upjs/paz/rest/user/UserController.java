package sk.upjs.paz.rest.user;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.web.bind.annotation.*;
import sk.upjs.paz.exceptions.NotFoundException;
import sk.upjs.paz.user.Role;
import sk.upjs.paz.user.UserDao;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserDao userDao;
    private final JdbcOperations jdbc;

    public UserController(UserDao userDao, JdbcOperations jdbc) {
        this.userDao = userDao;
        this.jdbc = jdbc;
    }

    @GetMapping
    public List<UserDto> getAll() {
        return userDao.getAll().stream().map(UserMapper::toDto).toList();
    }

    @GetMapping("/role/{role}")
    public List<UserDto> getByRole(@PathVariable Role role) {
        return userDao.getByRole(role).stream().map(UserMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable long id) {
        return UserMapper.toDto(userDao.getById(id));
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserUpsertDto body) {
        var created = userDao.create(UserMapper.fromUpsert(null, body));
        var dto = UserMapper.toDto(created);
        return ResponseEntity.created(URI.create("/api/users/" + dto.id())).body(dto);
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable long id, @Valid @RequestBody UserUpsertDto body) {
        userDao.getById(id);
        var updated = userDao.update(UserMapper.fromUpsert(id, body));
        return UserMapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        int rows = jdbc.update("DELETE FROM user WHERE id = ?", id);
        if (rows == 0) {
            throw new NotFoundException("User with id " + id + " not found.");
        }
        return ResponseEntity.noContent().build();
    }
}
