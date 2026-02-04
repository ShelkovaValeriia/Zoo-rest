package sk.upjs.paz.enclosure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Enclosure {
    private Long id;
    private String name;
    private String zone;
    private LocalDateTime lastMaintainance;
    private Integer animalsCount;

    public Enclosure() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getZone() { return zone; }
    public void setZone(String zone) { this.zone = zone; }

    public LocalDateTime getLastMaintainance() { return lastMaintainance; }
    public void setLastMaintainance(LocalDateTime lastMaintainance) { this.lastMaintainance = lastMaintainance; }

    public Integer getAnimalsCount() { return animalsCount; }
    public void setAnimalsCount(Integer animalsCount) { this.animalsCount = animalsCount; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enclosure enclosure = (Enclosure) o;
        return id != null && id.equals(enclosure.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        String formattedLastMaintainance = (lastMaintainance != null) ? lastMaintainance.format(formatter) : "Ešte neprebehla";
        return String.format("Názov: %s\nZóna: %s", name, zone);
    }

    public static Enclosure fromResultSet(ResultSet rs) throws SQLException {
        return fromResultSet(rs, "");
    }

    public static Enclosure fromResultSet(ResultSet rs, String aliasPrefix) throws SQLException {
        var id = rs.getLong(aliasPrefix + "id");
        if (rs.wasNull()) return null;

        Enclosure enclosure = new Enclosure();
        enclosure.setId(id);
        enclosure.setName(rs.getString(aliasPrefix + "name"));
        enclosure.setZone(rs.getString(aliasPrefix + "zone"));

        Timestamp ts = rs.getTimestamp(aliasPrefix + "last_maintainance");
        enclosure.setLastMaintainance(ts != null ? ts.toLocalDateTime() : null);

        enclosure.setAnimalsCount(null);
        return enclosure;
    }
}
