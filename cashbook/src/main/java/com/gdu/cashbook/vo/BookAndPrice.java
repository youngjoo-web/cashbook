package com.gdu.cashbook.vo;

public class BookAndPrice {
	private int bookId;
	private String bookName;
	private int price;
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "BookAndPrice [bookId=" + bookId + ", bookName=" + bookName + ", price=" + price + "]";
	}
}
