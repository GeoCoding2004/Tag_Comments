package com.georgehabib.Tags_comments.Comments;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.georgehabib.Tags_comments.Tags.Tags;
import com.georgehabib.Tags_comments.Users.User;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comments {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

    private String content;

    @ManyToOne     
    @JoinColumn(name = "tag_id", nullable = false)
    @JsonBackReference
    private Tags tags;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Comments() {}

    public Comments(String content, Tags tags, LocalDateTime createdAt, LocalDateTime updatedAt){
        this.content = content;
        this.tags = tags;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

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

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}

    public Tags getTags() { return tags; }
    public void setTags(Tags tags) { this.tags = tags; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

}
