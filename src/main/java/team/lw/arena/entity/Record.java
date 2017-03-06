package team.lw.arena.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2017/3/2.
 */
@Entity
public class Record {
    private Timestamp date;
    private String position;
    private String type;
    private String raceNumber;
    private String id01;
    private String id06;
    private String id02;
    private String id03;
    private String id04;
    private String id05;

    public Record(Timestamp date, String position, String type, String raceNumber, String id01, String id02, String id03, String id04, String id05, String id06) {
        this.date = date;
        this.position = position;
        this.type = type;
        this.raceNumber = raceNumber;
        this.id01 = id01;
        this.id06 = id06;
        this.id02 = id02;
        this.id03 = id03;
        this.id04 = id04;
        this.id05 = id05;
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

    @Id
    @Column(name = "_race_number")
    public String getRaceNumber() {
        return raceNumber;
    }

    public void setRaceNumber(String raceNumber) {
        this.raceNumber = raceNumber;
    }

    @Basic
    @Column(name = "_id_01")
    public String getId01() {
        return id01;
    }

    public void setId01(String id01) {
        this.id01 = id01;
    }

    @Basic
    @Column(name = "_id_06")
    public String getId06() {
        return id06;
    }

    public void setId06(String id06) {
        this.id06 = id06;
    }

    @Basic
    @Column(name = "_id_02")
    public String getId02() {
        return id02;
    }

    public void setId02(String id02) {
        this.id02 = id02;
    }

    @Basic
    @Column(name = "_id_03")
    public String getId03() {
        return id03;
    }

    public void setId03(String id03) {
        this.id03 = id03;
    }

    @Basic
    @Column(name = "_id_04")
    public String getId04() {
        return id04;
    }

    public void setId04(String id04) {
        this.id04 = id04;
    }

    @Basic
    @Column(name = "_id_05")
    public String getId05() {
        return id05;
    }

    public void setId05(String id05) {
        this.id05 = id05;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Record record = (Record) o;

        if (date != null ? !date.equals(record.date) : record.date != null) return false;
        if (position != null ? !position.equals(record.position) : record.position != null) return false;
        if (type != null ? !type.equals(record.type) : record.type != null) return false;
        if (raceNumber != null ? !raceNumber.equals(record.raceNumber) : record.raceNumber != null) return false;
        if (id01 != null ? !id01.equals(record.id01) : record.id01 != null) return false;
        if (id06 != null ? !id06.equals(record.id06) : record.id06 != null) return false;
        if (id02 != null ? !id02.equals(record.id02) : record.id02 != null) return false;
        if (id03 != null ? !id03.equals(record.id03) : record.id03 != null) return false;
        if (id04 != null ? !id04.equals(record.id04) : record.id04 != null) return false;
        if (id05 != null ? !id05.equals(record.id05) : record.id05 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (raceNumber != null ? raceNumber.hashCode() : 0);
        result = 31 * result + (id01 != null ? id01.hashCode() : 0);
        result = 31 * result + (id06 != null ? id06.hashCode() : 0);
        result = 31 * result + (id02 != null ? id02.hashCode() : 0);
        result = 31 * result + (id03 != null ? id03.hashCode() : 0);
        result = 31 * result + (id04 != null ? id04.hashCode() : 0);
        result = 31 * result + (id05 != null ? id05.hashCode() : 0);
        return result;
    }
}
