package com.article.article.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class Comment extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne
    private Article article;

    @Setter
    @ManyToOne
    @JoinColumn(name = "userId")
    private UserAccount userAccount;

    @Setter
    @JoinColumn(name = "parentCommentId")
    @ManyToOne
    private Comment parentComment;

    @ToString.Exclude
    @OrderBy("createdAt ASC")
    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL)
    private final Set<Comment> childComments = new LinkedHashSet<>();

    @Setter
    @Column(nullable = false, length = 500)
    private String content;

    protected Comment() {
    }

    private Comment(Article article, UserAccount userAccount, String content) {
        this.article = article;
        this.userAccount = userAccount;
        this.content = content;
    }

    public static Comment of(Article article, UserAccount userAccount, String content) {
        return new Comment(article, userAccount, content);
    }

    public static Comment of(Article article, UserAccount userAccount, Comment parentComment, String content) {
        return new Comment(article, userAccount, content);
    }

    public void addChildComment(Comment child) {
        //child.setParentComment(this.getId());
        this.getChildComments().add(child);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
