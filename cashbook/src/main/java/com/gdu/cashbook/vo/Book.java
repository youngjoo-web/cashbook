package com.gdu.cashbook.vo;

public class Book {
	private int bookId;
	private String memberId;
	private String memberName;
	private String book_pic;
	private String bookName;
	private String bookMemo;
	private String bookYear;
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", memberId=" + memberId + ", memberName=" + memberName + ", book_pic="
				+ book_pic + ", bookName=" + bookName + ", bookMemo=" + bookMemo + ", bookYear=" + bookYear + "]";
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getBook_pic() {
		return book_pic;
	}
	public void setBook_pic(String book_pic) {
		this.book_pic = book_pic;
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
	public String getBookYear() {
		return bookYear;
	}
	public void setBookYear(String bookYear) {
		this.bookYear = bookYear;
	}
	
}
