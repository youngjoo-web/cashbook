package com.gdu.cashbook.vo;

public class Category {
	private String categoryName;
	@Override
	public String toString() {
		return "Category [categoryName=" + categoryName + ", categoryDesc=" + categoryDesc + "]";
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryDesc() {
		return categoryDesc;
	}
	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
	private String categoryDesc;
}
