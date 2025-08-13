 package com.georgehabib.Tags_comments.Comments;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentCreateDto {
    private Integer tagId;

    @NotBlank(message = "Comment content must not be blank")
    private String content;
}
