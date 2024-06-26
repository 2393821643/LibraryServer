package com.mata.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mata.dao.BookDao;
import com.mata.dto.BookDto;
import com.mata.dto.PageResult;
import com.mata.dto.Result;
import com.mata.exception.BusinessException;
import com.mata.pojo.Book;
import com.mata.service.BookService;
import com.mata.util.CosClientUtil;
import com.mata.util.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class BookServiceImpl extends ServiceImpl<BookDao, Book> implements BookService {
    @Autowired
    private CosClientUtil cosClientUtil;
    @Value("${file.path}")
    private String tempFilePath;

    /**
     * 添加书本
     */
    @Override
    public Result addBook(BookDto bookDto) {
        // 校验数据
        if (bookDto.getBookName() == null || bookDto.getBookAuthor() == null || bookDto.getBookImg() == null){
            return Result.error("信息不完整");
        }
        Integer userId = UserHolder.getUser();
        // 保存图片
        String imgUrl = saveImg(bookDto.getBookImg());
        Book book = Book.builder()
                .bookName(bookDto.getBookName())
                .bookAuthor(bookDto.getBookAuthor())
                .bookImg(imgUrl)
                .bookISBN(bookDto.getBookISBN())
                .userId(userId)
                .build();
        // 保存书本信息
        save(book);
        return Result.success(null,"上传成功");
    }

    /**
     *  添加书本
     */
    @Override
    public Result<PageResult> getBookList(Integer currentPageCount) {
        // 查询条件
        LambdaQueryWrapper<Book> wrapper = new LambdaQueryWrapper<Book>()
                .eq(Book::getUserId,UserHolder.getUser());
        Page<Book> page = Page.of(currentPageCount, 10);
        // 查询
        Page<Book> resultPage = this.page(page, wrapper);
        // 封装对象
        PageResult pageResult = new PageResult(resultPage.getPages(), resultPage.getRecords());
        return Result.success(pageResult,null);
    }

    /**
     *  书本模糊查询
     */
    @Override
    public Result<List<Book>> getBookByName(String bookName) {
        LambdaQueryWrapper<Book> wrapper = new LambdaQueryWrapper<Book>()
                .eq(Book::getUserId,UserHolder.getUser())
                .like(Book::getBookName,bookName);
        List<Book> list = this.list(wrapper);
        return Result.success(list,null);
    }

    /**
     * 修改书本信息
     */
    @Override
    public Result updateBook(BookDto bookDto) {
        // 校验数据
        if (bookDto.getBookName() == null || bookDto.getBookAuthor() == null || bookDto.getBookId() == null){
            return Result.error("信息不完整");
        }
        // 寻找此bookid
        Book book = this.getById(bookDto.getBookId());
        if (!UserHolder.getUser().equals(book.getUserId())){
            return Result.error("不能修改他人的书籍");
        }
        book.setBookName(bookDto.getBookName());
        book.setBookAuthor(bookDto.getBookAuthor());
        book.setBookISBN(bookDto.getBookISBN());
        // 如果需要修改图片
        if (bookDto.getBookImg()!= null){
            String url = saveImg(bookDto.getBookImg());
            book.setBookImg(url);
        }
        this.updateById(book);
        return Result.success(null,"修改成功");
    }

    /**
     *  删除书本
     */
    @Override
    public Result deleteBook(Integer bookId) {
        // 寻找此bookid
        Book book = this.getById(bookId);
        if (!UserHolder.getUser().equals(book.getUserId())){
            return Result.error("不能删除他人的书籍");
        }
        this.removeById(bookId);
        return Result.success(null,"删除成功");
    }

    /**
     * 将图片上传图床
     *
     * @param image 图片
     */
    private String saveImg(MultipartFile image) {
        try {
            //将image转file 并写入本地
            byte[] fileBytes = image.getBytes();
            File convertedFile = new File(tempFilePath + image.getOriginalFilename());
            FileOutputStream fos = new FileOutputStream(convertedFile);
            fos.write(fileBytes);
            fos.close();
            //写入cos
            String imgUrl = cosClientUtil.sendFile(convertedFile);
            //删除本地File
            convertedFile.delete();
            return imgUrl;
        } catch (IOException e) {
            throw new BusinessException("不是文件");
        }
    }

}
