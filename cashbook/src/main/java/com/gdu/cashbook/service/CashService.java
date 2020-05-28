package com.gdu.cashbook.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdu.cashbook.mapper.CashMapper;
import com.gdu.cashbook.vo.Cash;
import com.gdu.cashbook.vo.DayAndPrice;
import com.gdu.cashbook.vo.LoginMember;
@Service
@Transactional
public class CashService {
	
	@Autowired private CashMapper cashMapper;
	
	public int addCash(Cash cash) {
		System.out.println(cash);
		return cashMapper.insertCash(cash);
	}
	public int removeCash(String cashNo) {
		System.out.println(cashNo);
		int cashno = Integer.parseInt(cashNo);
		
		return cashMapper.deleteCash(cashno);
	}
	public int modifyCash(Cash cash) {
		System.out.println(cash);
		return cashMapper.updateCash(cash);
	}
	public Cash getCashOne(String cashNo){
		int cashno=Integer.parseInt(cashNo);
		return cashMapper.selectCashOne(cashno);
	}
	public List<DayAndPrice> getDayAndPriceList(String memberId, int year, int month) {
		Map<String, Object> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("year", year);
		map.put("month", month);
		return cashMapper.selectDayAndPriceList(map);
	}
	public List<Cash> getCashListByDate(Cash cash){
		List<Cash>list = new ArrayList<>();
		list = cashMapper.selectCashListByDate(cash);
		return list;
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
