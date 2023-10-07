package com.munsal.java21demo.domain.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.munsal.java21demo.domain.jsonView.ViewRole;
import lombok.*;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BookDto {
    @JsonView({ViewRole.DeleteRequest.class, ViewRole.ViewRequest.class})
    private Long id;

    @NotEmpty(message = "Title field cannot be empty")
    @JsonView({ViewRole.ViewRequest.class, ViewRole.AddRequest.class, ViewRole.UpsertRequest.class})
    private String title;

    @NotEmpty(message = "Author field cannot be empty")
    @JsonView({ViewRole.ViewRequest.class, ViewRole.AddRequest.class, ViewRole.UpsertRequest.class})
    private String author;

    @NotEmpty(message = "ISBN field cannot be empty")
    @JsonView({ViewRole.ViewRequest.class, ViewRole.AddRequest.class, ViewRole.UpsertRequest.class})
    private String isbn;

    @NotEmpty(message = "Publisher field cannot be empty")
    @JsonView({ViewRole.ViewRequest.class, ViewRole.AddRequest.class, ViewRole.UpsertRequest.class})
    private String publisher;

    @NotNull(message = "Publish date cannot be empty.")
    @JsonView({ViewRole.ViewRequest.class, ViewRole.AddRequest.class, ViewRole.UpsertRequest.class})
    private Date publishDate;
}
