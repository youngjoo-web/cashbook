package com.gdu.cashbook.vo;

public class BookAndPrice {
	private String bookYear;
	private String bookName;
	private int price;
	public String getBookYear() {
		return bookYear;
	}
	public void setBookYear(String bookYear) {
		this.bookYear = bookYear;
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
		return "BookAndPrice [bookYear=" + bookYear + ", bookName=" + bookName + ", price=" + price + "]";
	}
	
}
