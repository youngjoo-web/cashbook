package com.gdu.cashbook.mapper;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.cashbook.vo.Cash;
import com.gdu.cashbook.vo.DayAndPrice;
import com.gdu.cashbook.vo.LoginMember;

@Mapper
public interface CashMapper {
	public int deleteCashByMember(LoginMember loginMember);
	
	public int insertCash(Cash cash);
	
	public int updateCash(Cash cash);
	
	public int deleteCash(int cashNo);
	
	public List<DayAndPrice> selectDayAndPriceList(Map<String, Object> map);
	
	public Cash selectCashOne(int cashNo);
	
	public ArrayList<Cash> selectCashListByDate(Cash cash);
	
	public int selectCashKindSum(Cash cash);
}
