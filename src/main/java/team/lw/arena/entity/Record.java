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
    private int raceNumber;
    private Timestamp date;
    private String position;
    private String type;
    private String id01;
    private String id06;
    private String id02;
    private String id03;
    private String id04;
    private String id05;

    public Record(Timestamp date, String position, String type, String id01, String id02, String id03, String id04, String id05, String id06) {
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

    public Record() {
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
    public int getRaceNumber() {
        return raceNumber;
    }

    public void setRaceNumber(int raceNumber) {
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
    public String toString() {
        return "Record{" +
                "date=" + date +
                ", position='" + position + '\'' +
                ", type='" + type + '\'' +
                ", raceNumber='" + raceNumber + '\'' +
                ", id01='" + id01 + '\'' +
                ", id06='" + id06 + '\'' +
                ", id02='" + id02 + '\'' +
                ", id03='" + id03 + '\'' +
                ", id04='" + id04 + '\'' +
                ", id05='" + id05 + '\'' +
                '}';
    }
}
