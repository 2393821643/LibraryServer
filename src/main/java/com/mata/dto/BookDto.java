package com.mata.dto;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BookDto {
    private Integer bookId;
    private String bookName;
    private String bookAuthor;
    private String bookISBN;
    private Integer userId;
    private MultipartFile bookImg;
}
