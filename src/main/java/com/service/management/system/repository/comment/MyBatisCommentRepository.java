package com.service.management.system.repository.comment;

import com.service.management.system.domain.comment.Comment;
import com.service.management.system.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisCommentRepository implements CommentRepository {
    private final CommentMapper commentMapper;
    @Override
    public int insert(Comment comment) {
        return commentMapper.insert(comment);
    }
    @Override
    public List<Comment> list(Comment comment) {
        return commentMapper.list(comment);
    }
    @Override
    public int getMaxStep(Comment comment) {
        return commentMapper.getMaxStep(comment);
    }
    @Override
    public int updateStep(Comment comment) {
        return commentMapper.updateStep(comment);
    }
    @Override
    public int getMaxGroupNo(Comment comment) {
        return commentMapper.getMaxGroupNo(comment);
    }
}
