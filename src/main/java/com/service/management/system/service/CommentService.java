package com.service.management.system.service;

import com.service.management.system.domain.comment.Comment;
import com.service.management.system.dto.comment.CommentWriteDto;
import com.service.management.system.repository.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private static final String TABLE_NAME = "comments";
    private final FileObjectService fileObjectService;
    private final CommentRepository commentRepository;
    public Comment write(CommentWriteDto commentWriteDto) {
        Comment param = Comment.builder()
                .projectFk(commentWriteDto.getProjectFk())
                .memberFk(commentWriteDto.getMemberFk())
                .contents(commentWriteDto.getContents())
                .groupNo(commentWriteDto.getGroupNo())
                .step(commentWriteDto.getStep())
                .depth(commentWriteDto.getDepth())
                .build();

        int insertResult = 0;
        if (isRootComment(commentWriteDto)) {
            // 댓글을 생성할 때
            log.info("create root comment");
            int maxGroupNo = commentRepository.getMaxGroupNo(param);
            param.setGroupNo(maxGroupNo + 1);
            param.setStep(commentWriteDto.getStep() + 1);
            insertResult = commentRepository.insert(param);
        } else {
            // 대댓글을 생성할 때
            log.info("create reply comment");
            int maxStep = commentRepository.getMaxStep(param);
            param.setStep(maxStep + 1);
            param.setDepth(param.getDepth() + 1);
            commentRepository.updateStep(param);
            insertResult = commentRepository.insert(param);

        }

        if (insertResult > 0) {
            fileObjectService.insertFiles(commentWriteDto.getFiles(), param.getNo(), TABLE_NAME);
            return param;
        }
        return null;
    }
    public List<Comment> list(Comment comment) {
        return commentRepository.list(comment);
    }
    private boolean isRootComment(CommentWriteDto commentWriteDto) {
        return commentWriteDto.getStep() == 0 && commentWriteDto.getDepth() == 0;
    }
}
