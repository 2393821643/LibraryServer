package com.mata.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mata.pojo.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookDao extends BaseMapper<Book> {
}
