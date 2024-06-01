package com.mata.controller;

import com.mata.dto.BookDto;
import com.mata.dto.PageResult;
import com.mata.dto.Result;
import com.mata.pojo.Book;
import com.mata.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    /**
     *  获取书本分页
     */
    @GetMapping("/list/{page}")
    public Result<PageResult> getBooksList(@PathVariable("page") Integer page){
        return bookService.getBookList(page);
    }

    /**
     *  获取书本 模糊查询
     */
    @GetMapping("/{bookName}")
    public Result<List<Book>> getBooksByName(@PathVariable("bookName") String bookName){
        return bookService.getBookByName(bookName);
    }

    /**
     *  修改书本
     */
    @PutMapping
    public Result updateBook(BookDto bookDto){
        return bookService.updateBook(bookDto);
    }

    /**
     *  删除书本
     */
    @DeleteMapping("/{bookId}")
    public Result deleteBook(@PathVariable("bookId") Integer bookId){
        return bookService.deleteBook(bookId);
    }

}
