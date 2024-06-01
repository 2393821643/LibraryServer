package com.mata.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mata.dao.BookDao;
import com.mata.dto.BookDto;
import com.mata.dto.Result;
import com.mata.exception.BusinessException;
import com.mata.pojo.Book;
import com.mata.service.BookService;
import com.mata.util.CosClientUtil;
import com.mata.util.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class BookServiceImpl extends ServiceImpl<BookDao, Book> implements BookService {
    @Autowired
    private CosClientUtil cosClientUtil;

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
     * 将图片上传图床
     *
     * @param image 图片
     */
    private String saveImg(MultipartFile image) {
        try {
            //将image转file 并写入本地
            byte[] fileBytes = image.getBytes();
            File convertedFile = new File("src/main/resources/static/images/" + image.getOriginalFilename());
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
