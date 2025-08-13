package com.georgehabib.Tags_comments.Tags;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
public class TagDTO {

    @NotBlank(message = "Tag name must not be blank")
    private String name;
}
