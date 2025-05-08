package com.service.management.system.controller.mvc;

import com.service.management.system.domain.comment.Comment;
import com.service.management.system.dto.comment.CommentWriteDto;
import com.service.management.system.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/write")
    public String write(@ModelAttribute CommentWriteDto commentWriteDto) {
        Comment comment = commentService.write(commentWriteDto);
        if (comment == null) {
            return "redirect:/projects/" + commentWriteDto.getProjectFk() + "?error";
        }
        return "redirect:/projects/" + commentWriteDto.getProjectFk();
    }
}
