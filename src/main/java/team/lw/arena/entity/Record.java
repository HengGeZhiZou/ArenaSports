package team.lw.arena.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;


@Entity
public class Record {
    private int count;
    private String id;
    private Timestamp date;
    private String position;
    private String type;

    @Id
    @Column(name = "_count")
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Basic
    @Column(name = "_id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "_date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "_position")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Basic
    @Column(name = "_type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Record record = (Record) o;

        if (count != record.count) return false;
        if (id != null ? !id.equals(record.id) : record.id != null) return false;
        if (date != null ? !date.equals(record.date) : record.date != null) return false;
        if (position != null ? !position.equals(record.position) : record.position != null) return false;
        if (type != null ? !type.equals(record.type) : record.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = count;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Record{" +
                "count=" + count +
                ", id='" + id + '\'' +
                ", date=" + date +
                ", position='" + position + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
