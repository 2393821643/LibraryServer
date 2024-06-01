package com.mata.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mata.dto.BookDto;
import com.mata.dto.PageResult;
import com.mata.dto.Result;
import com.mata.pojo.Book;

import java.util.List;

public interface BookService extends IService<Book> {
    /**
     *  添加书本
     */
    Result addBook(BookDto bookDto);

    /**
     *  书本列表分页
     */
    Result<PageResult> getBookList(Integer page);

    /**
     *  书本模糊查询
     */
    Result<List<Book>> getBookByName(String bookName);

    /**
     * 修改书本信息
     */
    Result updateBook(BookDto bookDto);

    /**
     *  删除书本
     */
    Result deleteBook(Integer bookId);
}
