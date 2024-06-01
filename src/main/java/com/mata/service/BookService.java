package com.mata.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mata.dto.BookDto;
import com.mata.dto.Result;
import com.mata.pojo.Book;

public interface BookService extends IService<Book> {
    /**
     *  添加书本
     */
    Result addBook(BookDto bookDto);
}
