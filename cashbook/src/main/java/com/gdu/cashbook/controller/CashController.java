package com.gdu.cashbook.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




import com.gdu.cashbook.service.CashService;
import com.gdu.cashbook.vo.Cash;
import com.gdu.cashbook.vo.LoginMember;

@Controller
public class CashController {
	@Autowired
	private CashService cashService;
	
	@GetMapping("/getCashListByDate")
	public String getCashListMyDate(HttpSession session, Model model, @RequestParam(value="day", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate day) {
		if(day == null) {
			day = LocalDate.now();
		}
		System.out.println(day + "<---day");
		if(session.getAttribute("loginMember") == null) {
			return "redirect:/";
		} 
		String loginMemberId = ((LoginMember)session.getAttribute("loginMember")).getMemberId();
		Cash cash = new Cash();
		cash.setCashDate(day.toString());
		cash.setMemberId(loginMemberId);
		
		List<Cash> cashList = cashService.getCashListByDate(cash);
		int sum = cashService.getCashKindSum(cash);
		model.addAttribute("cashList",cashList);
		model.addAttribute("day",day);
		model.addAttribute("sum",sum);
		
		for(Cash c : cashList) {
			System.out.print(c+"---c");
		}
		return "getCashListByDate";
	}
}
