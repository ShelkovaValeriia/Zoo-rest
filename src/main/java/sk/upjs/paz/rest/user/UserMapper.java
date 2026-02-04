package sk.upjs.paz.rest.user;

import sk.upjs.paz.user.User;

public class UserMapper {

    public static UserDto toDto(User u) {
        return new UserDto(
                u.getId(),
                u.getFirstName(),
                u.getLastName(),
                u.getGender(),
                u.getBirthDay(),
                u.getRole(),
                u.getEmail()
        );
    }

    public static User fromUpsert(Long id, UserUpsertDto dto) {
        User u = new User();
        u.setId(id);
        u.setFirstName(dto.firstName());
        u.setLastName(dto.lastName());
        u.setGender(dto.gender());
        u.setBirthDay(dto.birthDay());
        u.setRole(dto.role());
        u.setEmail(dto.email());
        u.setPassword(dto.password());
        return u;
    }
}
