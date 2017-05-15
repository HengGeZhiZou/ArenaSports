package team.lw.arena.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Record {
    private Timestamp date;
    private String position;
    private String type;

//    返回的用户的id IMG Name
    private String id01;
    private String id01Img;
    private String id01Name;
    private String id02;
    private String id02Img;
    private String id02Name;
    private String id03;
    private String id03Img;
    private String id03Name;
    private String id04;
    private String id04Img;
    private String id04Name;
    private String id05;
    private String id05Img;
    private String id05Name;
    private String id06;
    private String id06Img;
    private String id06Name;

//得到用户名和头像
//    private String img;
//    private String name;
    private String recordID;



    public Record(String recordID,Timestamp date, String position, String type, String id01, String id02, String id03, String id04, String id05, String id06) {
        this.recordID=recordID;
        this.date = date;
        this.position = position;
        this.type = type;
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

//    public String getImg() {
//        return img;
//    }
//
//    public void setImg(String img) {
//        this.img = img;
//    }

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
    @Column(name = "_recordID")
    public String getRecordID() {
        return recordID;
    }

    public void setRecordID(String recordID) {
        this.recordID = recordID;
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


    public String getId01Img() {
        return id01Img;
    }

    public void setId01Img(String id01Img) {
        this.id01Img = id01Img;
    }

    public String getId01Name() {
        return id01Name;
    }

    public void setId01Name(String id01Name) {
        this.id01Name = id01Name;
    }

    public String getId02Img() {
        return id02Img;
    }

    public void setId02Img(String id02Img) {
        this.id02Img = id02Img;
    }

    public String getId02Name() {
        return id02Name;
    }

    public void setId02Name(String id02Name) {
        this.id02Name = id02Name;
    }

    public String getId03Img() {
        return id03Img;
    }

    public void setId03Img(String id03Img) {
        this.id03Img = id03Img;
    }

    public String getId03Name() {
        return id03Name;
    }

    public void setId03Name(String id03Name) {
        this.id03Name = id03Name;
    }

    public String getId04Img() {
        return id04Img;
    }

    public void setId04Img(String id04Img) {
        this.id04Img = id04Img;
    }

    public String getId04Name() {
        return id04Name;
    }

    public void setId04Name(String id04Name) {
        this.id04Name = id04Name;
    }

    public String getId05Img() {
        return id05Img;
    }

    public void setId05Img(String id05Img) {
        this.id05Img = id05Img;
    }

    public String getId05Name() {
        return id05Name;
    }

    public void setId05Name(String id05Name) {
        this.id05Name = id05Name;
    }

    public String getId06Img() {
        return id06Img;
    }

    public void setId06Img(String id06Img) {
        this.id06Img = id06Img;
    }

    public String getId06Name() {
        return id06Name;
    }

    public void setId06Name(String id06Name) {
        this.id06Name = id06Name;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    @Override
    public String toString() {
        return "Record{" +
                "date=" + date +
                ", position='" + position + '\'' +
                ", type='" + type + '\'' +
                ", id01='" + id01 + '\'' +
                ", id01Img='" + id01Img + '\'' +
                ", id01Name='" + id01Name + '\'' +
                ", id02='" + id02 + '\'' +
                ", id02Img='" + id02Img + '\'' +
                ", id02Name='" + id02Name + '\'' +
                ", id03='" + id03 + '\'' +
                ", id03Img='" + id03Img + '\'' +
                ", id03Name='" + id03Name + '\'' +
                ", id04='" + id04 + '\'' +
                ", id04Img='" + id04Img + '\'' +
                ", id04Name='" + id04Name + '\'' +
                ", id05='" + id05 + '\'' +
                ", id05Img='" + id05Img + '\'' +
                ", id05Name='" + id05Name + '\'' +
                ", id06='" + id06 + '\'' +
                ", id06Img='" + id06Img + '\'' +
                ", id06Name='" + id06Name + '\'' +
//                ", img='" + img + '\'' +
                ", recordID='" + recordID + '\'' +
                '}';
    }
}
