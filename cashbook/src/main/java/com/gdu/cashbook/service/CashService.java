package com.gdu.cashbook.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.gdu.cashbook.mapper.CashMapper;
import com.gdu.cashbook.vo.Book;
import com.gdu.cashbook.vo.BookAndPrice;
import com.gdu.cashbook.vo.Cash;
import com.gdu.cashbook.vo.DayAndPrice;
@Service
@Transactional
public class CashService {
	
	@Autowired private CashMapper cashMapper;
	
	public int getBookId(Book book) {
		return cashMapper.selectBookId(book);
	}
	public int addCash(Cash cash) {
		return cashMapper.insertCash(cash);
	}
	public int removeCash(Cash cash) {
		return cashMapper.deleteCash(cash);
	}
	public int modifyCash(Cash cash) {
		return cashMapper.updateCash(cash);
	}
	public List<DayAndPrice> getDayAndPriceList(String memberId, int year, int month, int bookId) {
		Map<String, Object> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("year", year);
		map.put("month", month);
		map.put("bookId", bookId);
		return cashMapper.selectDayAndPriceList(map);
	}
	
	public List<Cash> getCashListByDate(Cash cash){
		List<Cash>list = new ArrayList<>();
		list = cashMapper.selectCashListByDate(cash);
		return list;
	}
	public List<BookAndPrice> getBookAndPriceList(String memberId, String year){
		Map<String, Object> map = new HashMap<>();
		map.put("year", year);
		map.put("memberId", memberId);
		List<BookAndPrice> list = cashMapper.selectBookAndPriceList(map);
		return list;
	}
	public int getYearAndPriceSum(String memberId, String Year) {
		Map<String, Object> map = new HashMap<>();
		map.put("year", Year);
		map.put("memberId", memberId);
		return cashMapper.selectYearAndPriceSum(map);
		
	}
	
	public int getCashKindSum(Cash cash) {
		int sum=0;
		try {
			sum = cashMapper.selectCashKindSum(cash);
		}catch(Exception e) {
			sum=0;
		}
		return sum;
	}
}
