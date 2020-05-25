package com.gdu.cashbook.vo;

import org.springframework.web.multipart.MultipartFile;

public class BookForm {
	private String bookId;
	private String bookName;
	private String bookMemo;
	private MultipartFile bookPic;
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookMemo() {
		return bookMemo;
	}
	public void setBookMemo(String bookMemo) {
		this.bookMemo = bookMemo;
	}
	public MultipartFile getBookPic() {
		return bookPic;
	}
	public void setBookPic(MultipartFile bookPic) {
		this.bookPic = bookPic;
	}
	@Override
	public String toString() {
		return "BookForm [bookId=" + bookId + ", bookName=" + bookName + ", bookMemo=" + bookMemo + ", bookPic="
				+ bookPic + "]";
	}
	
}
