package com.service.management.system.repository.comment;

import com.service.management.system.domain.comment.Comment;

import java.util.List;

public interface CommentRepository {
    int insert(Comment comment);
    List<Comment> list(Comment comment);
    void updateStep(Comment comment);
}
