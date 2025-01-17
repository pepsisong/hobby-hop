package com.hobbyhop.domain.commentreply.pk;

import com.hobbyhop.domain.comment.entity.Comment;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Embeddable
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class CommentReplyPK implements Serializable {

    @JoinColumn(name = "comment_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Comment comment;

    @JoinColumn(name = "reply_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Comment reply;

}
