package com.service.management.system.service;

import com.service.management.system.domain.comment.Comment;
import com.service.management.system.dto.comment.CommentWriteDto;
import com.service.management.system.repository.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private static final String TABLE_NAME = "comments";
    private final FileObjectService fileObjectService;
    private final CommentRepository commentRepository;
    public Comment write(CommentWriteDto commentWriteDto) {
        Comment comment = Comment.builder()
                .contents(commentWriteDto.getContents())
                .groupNo(commentWriteDto.getGroupNo())
                .step(commentWriteDto.getStep())
                .depth(commentWriteDto.getDepth())
                .projectFk(commentWriteDto.getProjectFk())
                .memberFk(commentWriteDto.getMemberFk())
                .build();
        commentRepository.updateStep(comment);
        int insertResult = commentRepository.insert(comment);
        if (insertResult > 0) {
            fileObjectService.insertFiles(commentWriteDto.getFiles(), comment.getNo(), TABLE_NAME);
            return comment;
        }
        return null;
    }
    public List<Comment> list(Comment comment) {
        return commentRepository.list(comment);
    }
}
