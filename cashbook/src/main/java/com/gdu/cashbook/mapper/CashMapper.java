package com.gdu.cashbook.mapper;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.cashbook.vo.Book;
import com.gdu.cashbook.vo.BookAndPrice;
import com.gdu.cashbook.vo.Cash;
import com.gdu.cashbook.vo.DayAndPrice;

@Mapper
public interface CashMapper {
	public int selectBookId(Book book);
	
	public int insertCash(Cash cash);
	
	public int deleteCash(Cash cash);
	
	public int updateCash(Cash cash);
	
	public List<DayAndPrice> selectDayAndPriceList(Map<String, Object> map);
	
	public int selectYearAndPriceSum(Map<String, Object> map);
	
	public List<BookAndPrice> selectBookAndPriceList(Map<String, Object> map);
	
	public ArrayList<Cash> selectCashListByDate(Cash cash);
	
	public int selectCashKindSum(Cash cash);
}
