package team.lw.arena.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/2/23.
 */
@Entity
@Table(name = "six_people_room", schema = "project_db", catalog = "")
public class SixPeopleRoom {
    private String character01;
    private String character02;
    private String character03;
    private String character04;
    private String character05;
    private String character06;

    @Id
    @Column(name = "_character_01_id")
    public String getCharacter01() {
        return character01;
    }

    public void setCharacter01(String character01) {
        this.character01 = character01;
    }

    @Basic
    @Column(name = "_character_02_id")
    public String getCharacter02() {
        return character02;
    }

    public void setCharacter02(String character02) {
        this.character02 = character02;
    }

    @Basic
    @Column(name = "_character_03_id")
    public String getCharacter03() {
        return character03;
    }

    public void setCharacter03(String character03) {
        this.character03 = character03;
    }

    @Basic
    @Column(name = "_character_04_id")
    public String getCharacter04() {
        return character04;
    }

    public void setCharacter04(String character04) {
        this.character04 = character04;
    }

    @Basic
    @Column(name = "_character_05_id")
    public String getCharacter05() {
        return character05;
    }

    public void setCharacter05(String character05) {
        this.character05 = character05;
    }

    @Basic
    @Column(name = "_character_06_id")
    public String getCharacter06() {
        return character06;
    }

    public void setCharacter06(String character06) {
        this.character06 = character06;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SixPeopleRoom that = (SixPeopleRoom) o;

        if (character01 != null ? !character01.equals(that.character01) : that.character01 != null) return false;
        if (character02 != null ? !character02.equals(that.character02) : that.character02 != null) return false;
        if (character03 != null ? !character03.equals(that.character03) : that.character03 != null) return false;
        if (character04 != null ? !character04.equals(that.character04) : that.character04 != null) return false;
        if (character05 != null ? !character05.equals(that.character05) : that.character05 != null) return false;
        if (character06 != null ? !character06.equals(that.character06) : that.character06 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = character01 != null ? character01.hashCode() : 0;
        result = 31 * result + (character02 != null ? character02.hashCode() : 0);
        result = 31 * result + (character03 != null ? character03.hashCode() : 0);
        result = 31 * result + (character04 != null ? character04.hashCode() : 0);
        result = 31 * result + (character05 != null ? character05.hashCode() : 0);
        result = 31 * result + (character06 != null ? character06.hashCode() : 0);
        return result;
    }
}
