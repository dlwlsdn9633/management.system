package com.service.management.system.service;

import com.service.management.system.domain.comment.Comment;
import com.service.management.system.dto.comment.CommentRequestDto;
import com.service.management.system.repository.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.support.DefaultConversionService;
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
    private final DefaultConversionService conversionService;
    public Comment write(CommentRequestDto commentRequestDto) {
        Comment commentParam = conversionService.convert(commentRequestDto, Comment.class);
        int insertResult = 0;
        if (isRootComment(commentRequestDto)) {
            // 댓글을 생성할 때
            log.info("create root comment");
            int maxGroupNo = commentRepository.getMaxGroupNo(commentParam);
            commentParam.setGroupNo(maxGroupNo + 1);
            commentParam.setStep(commentRequestDto.getStep() + 1);
            insertResult = commentRepository.insert(commentParam);
        } else {
            // 대댓글을 생성할 때
            log.info("create reply comment");
            int maxStep = commentRepository.getMaxStep(commentParam);
            commentParam.setStep(maxStep + 1);
            commentParam.setDepth(commentParam.getDepth() + 1);
            commentRepository.updateStep(commentParam);
            insertResult = commentRepository.insert(commentParam);

        }

        if (insertResult > 0) {
            fileObjectService.insertFiles(commentRequestDto.getFiles(), commentParam.getNo(), TABLE_NAME);
            return commentParam;
        }
        return null;
    }
    public List<Comment> list(Comment comment) {
        return commentRepository.list(comment);
    }
    private boolean isRootComment(CommentRequestDto commentRequestDto) {
        return commentRequestDto.getStep() == 0 && commentRequestDto.getDepth() == 0;
    }
}
