package com.georgehabib.Tags_comments.Comments;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor
public class CommentUpdateDto {

    @NotBlank(message = "Content must not be blank")
    private String content;
}