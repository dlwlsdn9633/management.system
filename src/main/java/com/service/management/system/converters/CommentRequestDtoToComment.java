package com.service.management.system.converters;

import com.service.management.system.domain.comment.Comment;
import com.service.management.system.dto.comment.CommentRequestDto;
import org.springframework.core.convert.converter.Converter;

public class CommentRequestDtoToComment implements Converter<CommentRequestDto, Comment> {
    @Override
    public Comment convert(CommentRequestDto source) {
        return Comment.builder()
                .projectFk(source.getProjectFk())
                .memberFk(source.getMemberFk())
                .contents(source.getContents())
                .groupNo(source.getGroupNo())
                .step(source.getStep())
                .depth(source.getDepth())
                .build();
    }
}
