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
@Service
@Transactional
public class CashService {
	
	@Autowired private CashMapper cashMapper;
	
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
