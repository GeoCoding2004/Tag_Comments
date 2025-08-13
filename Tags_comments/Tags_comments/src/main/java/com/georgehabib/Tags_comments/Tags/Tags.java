package com.georgehabib.Tags_comments.Tags;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.georgehabib.Tags_comments.Comments.Comments;

@Entity    
@Table(name = "tags")  
public class Tags {

    @Id     
    @GeneratedValue(strategy = GenerationType.IDENTITY)        
    private Integer id;     

    private String name;   

    @OneToMany(mappedBy = "tags", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Comments> comments = new ArrayList<>();  

    private LocalDateTime createdAt;    

    private LocalDateTime updatedAt;  


    public Tags() {}    

    public Tags(String name, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.name = name;
    }

    @PrePersist 
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate 
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }


    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Comments> getComments() { return comments; }
    public void setComments(List<Comments> comments) { this.comments = comments; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }


    public void addComment(Comments comment) {
        comments.add(comment);
        comment.setTags(this);
    }

    public void removeComment(Comments comment) {
        comments.remove(comment);
        comment.setTags(null);
    }

}
