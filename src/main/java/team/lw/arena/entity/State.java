package team.lw.arena.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2017/3/7.
 */
@Entity
public class State {
    private String sId;
    private String id;
    private String text;
    private Timestamp date;
    private String photo;
    private String position;
    private Integer like;
    private String commentsId;

    @Id
    @Column(name = "_s_id")
    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
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
    @Column(name = "_text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
    @Column(name = "_photo")
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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
    @Column(name = "_like")
    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    @Basic
    @Column(name = "_comments_id")
    public String getCommentsId() {
        return commentsId;
    }

    public void setCommentsId(String commentsId) {
        this.commentsId = commentsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        if (sId != null ? !sId.equals(state.sId) : state.sId != null) return false;
        if (id != null ? !id.equals(state.id) : state.id != null) return false;
        if (text != null ? !text.equals(state.text) : state.text != null) return false;
        if (date != null ? !date.equals(state.date) : state.date != null) return false;
        if (photo != null ? !photo.equals(state.photo) : state.photo != null) return false;
        if (position != null ? !position.equals(state.position) : state.position != null) return false;
        if (like != null ? !like.equals(state.like) : state.like != null) return false;
        if (commentsId != null ? !commentsId.equals(state.commentsId) : state.commentsId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sId != null ? sId.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (like != null ? like.hashCode() : 0);
        result = 31 * result + (commentsId != null ? commentsId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "State{" +
                "sId='" + sId + '\'' +
                ", id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", photo='" + photo + '\'' +
                ", position='" + position + '\'' +
                ", like=" + like +
                ", commentsId='" + commentsId + '\'' +
                '}';
    }
}
