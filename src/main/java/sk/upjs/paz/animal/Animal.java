package sk.upjs.paz.animal;

import sk.upjs.paz.enclosure.Enclosure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Animal {
    private Long id;
    private String nickname;
    private String species;
    private Sex sex;
    private LocalDate birthDay;
    private LocalDateTime lastCheck;
    private Status status;
    private Enclosure enclosure;

    public Animal() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }

    public Sex getSex() { return sex; }
    public void setSex(Sex sex) { this.sex = sex; }

    public LocalDate getBirthDay() { return birthDay; }
    public void setBirthDay(LocalDate birthDay) { this.birthDay = birthDay; }

    public LocalDateTime getLastCheck() { return lastCheck; }
    public void setLastCheck(LocalDateTime lastCheck) { this.lastCheck = lastCheck; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public Enclosure getEnclosure() { return enclosure; }
    public void setEnclosure(Enclosure enclosure) { this.enclosure = enclosure; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return id != null && id.equals(animal.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        String formattedLastCheck = (lastCheck != null) ? lastCheck.format(formatter) : "Ešte neprebehla";

        return String.format("Prezývka: %s\nDruh: %s\nPohlavie: %s\nVýbeh: %s\nPosledná Kontrola: %s",
                (nickname != null && !nickname.isEmpty()) ? nickname : "Žiadna prezývka",
                species,
                sex,
                (enclosure != null ? enclosure.getName() : "Nezadaný výbeh"),
                formattedLastCheck);
    }

    public static Animal fromResultSet(ResultSet rs) throws SQLException {
        return fromResultSet(rs, "");
    }

    public static Animal fromResultSet(ResultSet rs, String aliasPrefix) throws SQLException {
        var id = rs.getLong(aliasPrefix + "id");
        if (rs.wasNull()) return null;

        var animal = new Animal();
        animal.setId(id);
        animal.setNickname(rs.getString(aliasPrefix + "nickname"));
        animal.setSpecies(rs.getString(aliasPrefix + "species"));

        String sexStr = rs.getString(aliasPrefix + "sex");
        animal.setSex(sexStr != null ? Sex.valueOf(sexStr) : null);

        var bd = rs.getDate(aliasPrefix + "birth_day");
        animal.setBirthDay(bd != null ? bd.toLocalDate() : null);

        Timestamp ts = rs.getTimestamp(aliasPrefix + "last_check");
        animal.setLastCheck(ts != null ? ts.toLocalDateTime() : null);

        String stStr = rs.getString(aliasPrefix + "status");
        animal.setStatus(stStr != null ? Status.valueOf(stStr) : null);

        animal.setEnclosure(null);
        return animal;
    }
}
