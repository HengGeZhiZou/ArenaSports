package team.lw.arena.entity;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "mobile_user", schema = "project_db")
public class MobileUser {
    private String muUId;
    private BigDecimal muLongitud;
    private BigDecimal muLatitude;
    private int currPage;

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public MobileUser(String id, BigDecimal muLongitud, BigDecimal muLatitude) {
        this.muUId = id;
        this.muLongitud = muLongitud;
        this.muLatitude = muLatitude;
    }

    public MobileUser() {
    }

    @Id
    @Column(name = "mu_u_id")
    public String getMuUId() {
        return muUId;
    }

    public void setMuUId(String muUId) {
        this.muUId = muUId;
    }

    @Basic
    @Column(name = "mu_longitud")
    public BigDecimal getMuLongitud() {
        return muLongitud;
    }

    public void setMuLongitud(BigDecimal muLongitud) {
        this.muLongitud = muLongitud;
    }

    @Basic
    @Column(name = "mu_latitude")
    public BigDecimal getMuLatitude() {
        return muLatitude;
    }

    public void setMuLatitude(BigDecimal muLatitude) {
        this.muLatitude = muLatitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MobileUser that = (MobileUser) o;

        if (muUId != null ? !muUId.equals(that.muUId) : that.muUId != null) return false;
        if (muLongitud != null ? !muLongitud.equals(that.muLongitud) : that.muLongitud != null) return false;
        if (muLatitude != null ? !muLatitude.equals(that.muLatitude) : that.muLatitude != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = muUId != null ? muUId.hashCode() : 0;
        result = 31 * result + (muLongitud != null ? muLongitud.hashCode() : 0);
        result = 31 * result + (muLatitude != null ? muLatitude.hashCode() : 0);
        return result;
    }
}
