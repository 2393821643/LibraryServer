package com.mata.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@TableName("tb_book")
public class Book {
    @TableId(value="book_id", type= IdType.AUTO)
    private Integer bookId;
    @TableField("book_name")
    private String bookName;
    @TableField("book_author")
    private String bookAuthor;
    @TableField("book_isbn")
    private String bookISBN;
    @TableField("book_url")
    private String bookImg;
    @TableField("user_id")
    private Integer userId;
}
