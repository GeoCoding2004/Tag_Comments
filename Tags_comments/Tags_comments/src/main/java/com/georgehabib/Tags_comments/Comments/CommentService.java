package com.georgehabib.Tags_comments.Comments;

import com.georgehabib.Tags_comments.Tags.TagRepository;
import com.georgehabib.Tags_comments.Tags.Tags;
import com.georgehabib.Tags_comments.Users.User;
import com.georgehabib.Tags_comments.Users.UserRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import org.springframework.security.core.Authentication;

import java.util.List;

@Service 
public class CommentService {

    private final CommentRepository commentRepository;
    private final TagRepository tagRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired 
    public CommentService(CommentRepository commentRepository, TagRepository tagRepository) {
        this.commentRepository = commentRepository;
        this.tagRepository = tagRepository;
    }


    public Comments createComment(CommentCreateDto dto) {
        Tags tag = tagRepository.findById(dto.getTagId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag not found"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"));

        Comments comment = new Comments();
        comment.setContent(dto.getContent());
        comment.setTags(tag);
        comment.setUser(user);

        return commentRepository.save(comment);
    }


    public List<Comments> getCommentsByTags(Integer tagId) {
        Tags tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag not found"));

        return commentRepository.findByTags(tag);
    }


    public Comments getCommentById(Integer commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment with ID " + commentId + " not found"));
    }


    public Comments updateComment(Integer commentId, CommentUpdateDto dto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        Comments existing = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found"));

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!existing.getUser().getUsername().equals(username) &&
            authentication.getAuthorities().stream().noneMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
            
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to update this comment");
        }

        existing.setContent(dto.getContent());
        return commentRepository.save(existing);
    }


    public void deleteComment(Integer id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Comments comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found"));

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
    

        if (!comment.getUser().getUsername().equals(username) &&
            authentication.getAuthorities().stream().noneMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN")) ) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to delete this comment");
        }

        commentRepository.delete(comment);
    }


    @Transactional
    public void deleteCommentsByTags(Integer tagId) {
        Tags tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new IllegalArgumentException("Tag with ID " + tagId + " not found"));

        commentRepository.deleteByTags(tag);
    }



}
