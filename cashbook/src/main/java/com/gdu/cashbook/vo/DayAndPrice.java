package com.gdu.cashbook.vo;

public class DayAndPrice {
	private String day;
	private int price;
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "DayAndPrice [day=" + day + ", price=" + price + "]";
	}
}
