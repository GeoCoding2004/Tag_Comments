package com.georgehabib.Tags_comments.Tags;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/api/tags")
@CrossOrigin
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("")
    public List<Tags> getAllTags() {
        return tagService.getAllTags();
    }


    @GetMapping("/{id}")
    public Tags getTagbyId(@PathVariable Integer id) {
        return tagService.getTagById(id);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public Tags createTag(@Valid @RequestBody TagDTO dto) {
        return tagService.createTag(dto.getName());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Tags updateTag(@PathVariable Integer id, @Valid @RequestBody Tags updatedTag) {
        return tagService.updateTag(id, updatedTag);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTag(@PathVariable Integer id) {
        try {
            tagService.deleteTag(id);
            return ResponseEntity.ok("Tag deleted successfully"); 
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
        }
    }
}
