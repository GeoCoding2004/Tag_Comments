package com.georgehabib.Tags_comments.Comments;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @GetMapping("/tag/{tagId}")
    public ResponseEntity<?> getCommentsByTag(@PathVariable Integer tagId) {
        try {
            List<Comments> comments = commentService.getCommentsByTags(tagId);
            return ResponseEntity.ok(comments);
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
        }
    }


    @GetMapping("/{id}")
    public Comments getCommentById(@PathVariable Integer id) {
        return commentService.getCommentById(id);
    }

    @PostMapping("")
    public ResponseEntity<?> createComment(@Valid @RequestBody CommentCreateDto dto) {
        try {
            Comments createdComment = commentService.createComment(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(@PathVariable Integer id, @Valid @RequestBody CommentUpdateDto dto) {
         try {
        Comments updatedComment = commentService.updateComment(id, dto);
            return ResponseEntity.ok(updatedComment);} 
        catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());}
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer id) {
        try {
            commentService.deleteComment(id);
            return ResponseEntity.noContent().build(); 
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
        }
    }

}
