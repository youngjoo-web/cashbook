package com.gdu.cashbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gdu.cashbook.mapper.BookMapper;
import com.gdu.cashbook.vo.Book;

public class BookService {
	@Autowired private BookMapper bookMapper;
	public int addBook(Book book) {
		return bookMapper.insertBook(book);
	}
	public List<Book> getBookListByYear(String bookYear){
		List<Book>list = bookMapper.selectBookListByYear(bookYear);
		return list;
	}
	public int modifyBook(Book book) {
		return bookMapper.updateBook(book);
	}
	public void removeBook(Book book) {
		bookMapper.deleteCashByBook(book);
		bookMapper.deleteBook(book);
	}
}
