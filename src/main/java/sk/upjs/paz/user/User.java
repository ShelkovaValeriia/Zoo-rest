package sk.upjs.paz.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate birthDay;
    private Role role;
    private String email;
    private String password;

    public User() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }

    public LocalDate getBirthDay() { return birthDay; }
    public void setBirthDay(LocalDate birthDay) { this.birthDay = birthDay; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return String.format("%s %s (%s) (%d)", firstName, lastName, role, id);
    }

    public static User fromResultSet(ResultSet rs) throws SQLException {
        return fromResultSet(rs, "");
    }

    public static User fromResultSet(ResultSet rs, String aliasPrefix) throws SQLException {
        var id = rs.getLong(aliasPrefix + "id");
        if (rs.wasNull()) {
            return null;
        }

        var user = new User();
        user.setId(id);
        user.setFirstName(rs.getString(aliasPrefix + "first_name"));
        user.setLastName(rs.getString(aliasPrefix + "last_name"));

        String genderStr = rs.getString(aliasPrefix + "gender");
        user.setGender(genderStr != null ? Gender.valueOf(genderStr) : null);

        var date = rs.getDate(aliasPrefix + "birth_day");
        user.setBirthDay(date != null ? date.toLocalDate() : null);

        String roleStr = rs.getString(aliasPrefix + "role");
        user.setRole(roleStr != null ? Role.valueOf(roleStr) : null);

        user.setEmail(rs.getString(aliasPrefix + "email"));
        user.setPassword(rs.getString(aliasPrefix + "password"));
        return user;
    }
}
