package com.mata.controller;

import com.mata.dto.BookDto;
import com.mata.dto.Result;
import com.mata.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    /**
     *  添加书本
     */
    @PostMapping
    public Result addBook(BookDto bookDto){
        return bookService.addBook(bookDto);
    }
}
