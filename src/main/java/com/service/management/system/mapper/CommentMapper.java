package com.service.management.system.mapper;

import com.service.management.system.domain.comment.Comment;

import java.util.List;

public interface CommentMapper {
    int insert(Comment comment);
    List<Comment> list(Comment comment);
    int getMaxStep(Comment comment);
    int updateStep(Comment comment);
    int getMaxGroupNo(Comment comment);
}
