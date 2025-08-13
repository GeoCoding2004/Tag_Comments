package com.georgehabib.Tags_comments.Comments;

import com.georgehabib.Tags_comments.Tags.Tags;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Integer> {

    List<Comments> findByTags(Tags tags);
    
    void deleteByTags(Tags tags); 

}