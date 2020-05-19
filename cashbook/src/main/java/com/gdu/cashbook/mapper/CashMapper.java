package com.gdu.cashbook.mapper;

import java.awt.List;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.cashbook.vo.Cash;

@Mapper
public interface CashMapper {
	public ArrayList<Cash> selectCashListByDate(Cash cash);
}
