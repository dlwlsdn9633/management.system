package com.service.management.system.controller.mvc;

import com.service.management.system.domain.comment.Comment;
import com.service.management.system.dto.comment.CommentRequestDto;
import com.service.management.system.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
    public String write(@Valid @ModelAttribute CommentRequestDto commentRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("Validation Errors: {}", bindingResult.getAllErrors());
            return "redirect:/projects/" + commentRequestDto.getProjectFk() + "?error";
        }
        Comment createdComment = commentService.write(commentRequestDto);
        if (createdComment == null) {
            return "redirect:/projects/" + commentRequestDto.getProjectFk() + "?error";
        }
        return "redirect:/projects/" + commentRequestDto.getProjectFk();
    }
}
