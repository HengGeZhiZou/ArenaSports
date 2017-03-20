package team.lw.arena.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Comment {
    private String commentsId;
    private String reviewerId;
    private String content;
    private Timestamp commentTime;
    private int id;

    @Basic
    @Column(name = "comments_id")
    public String getCommentsId() {
        return commentsId;
    }

    public void setCommentsId(String commentsId) {
        this.commentsId = commentsId;
    }

    @Basic
    @Column(name = "reviewer_id")
    public String getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(String reviewerId) {
        this.reviewerId = reviewerId;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "comment_time")
    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (id != comment.id) return false;
        if (commentsId != null ? !commentsId.equals(comment.commentsId) : comment.commentsId != null) return false;
        if (reviewerId != null ? !reviewerId.equals(comment.reviewerId) : comment.reviewerId != null) return false;
        if (content != null ? !content.equals(comment.content) : comment.content != null) return false;
        if (commentTime != null ? !commentTime.equals(comment.commentTime) : comment.commentTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commentsId != null ? commentsId.hashCode() : 0;
        result = 31 * result + (reviewerId != null ? reviewerId.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (commentTime != null ? commentTime.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
