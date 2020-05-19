package com.gdu.cashbook.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gdu.cashbook.service.CashService;
import com.gdu.cashbook.vo.Cash;
import com.gdu.cashbook.vo.LoginMember;

@Controller
public class CashController {
	@Autowired
	private CashService cashService;
	
	@GetMapping("/getCashListByDate")
	public String getCashListMyDate(HttpSession session, Model model) {
		if(session.getAttribute("loginMember") == null) {
			return "redirect:/";
		} 
		String loginMemberId = ((LoginMember)session.getAttribute("loginMember")).getMemberId();
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strToday = sdf.format(today);
		System.out.println(strToday+"<--strToday");
		
		Cash cash = new Cash();
		cash.setCashDate(strToday);
		cash.setMemberId(loginMemberId);
		
		List<Cash> cashList = cashService.getCashListByDate(cash);
		model.addAttribute("cashList",cashList);
		model.addAttribute("today",today);
		
		for(Cash c : cashList) {
			System.out.print(c+"---c");
		}
		return "getCashListByDate";
	}
}
