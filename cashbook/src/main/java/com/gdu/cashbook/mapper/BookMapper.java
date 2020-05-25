package com.gdu.cashbook.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.cashbook.vo.Book;

@Mapper
public interface BookMapper {
	public List<Book> selectBookListByYear(String bookYear);
	
	public int insertBook(Book book);
	
	public int updateBook(Book book);
	
	public int deleteBook(Book book);
	
	public int deleteCashByBook(Book book);
	
}
