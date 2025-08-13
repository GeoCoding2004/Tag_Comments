package com.georgehabib.Tags_comments.Tags;

import com.georgehabib.Tags_comments.Comments.CommentRepository;
import com.georgehabib.Tags_comments.Exception.ResourceNotFoundException;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service 
public class TagService {

    private final TagRepository tagRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public TagService(TagRepository tagRepository, CommentRepository commentRepository) {
        this.tagRepository = tagRepository;
        this.commentRepository = commentRepository;
    }

    public Tags createTag(String name) {
        Tags tag = new Tags();
        tag.setName(name);
        return tagRepository.save(tag);
    }


    public List<Tags> getAllTags() {
        return tagRepository.findAll();
    }


    public Tags getTagById(Integer id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tag with ID " + id + " not found."));
    }


    public Tags updateTag(Integer id, Tags updatedTag) {
        Tags existingTag = getTagById(id); 
        existingTag.setName(updatedTag.getName());
        return tagRepository.save(existingTag);
    }


    @Transactional 
    public void deleteTag(Integer id) {
        Tags tag = getTagById(id);
        commentRepository.deleteByTags(tag); 
        tagRepository.delete(tag); 
    }

}
