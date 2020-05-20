package com.gdu.cashbook.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
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
import com.gdu.cashbook.vo.DayAndPrice;
import com.gdu.cashbook.vo.LoginMember;

@Controller
public class CashController {
	@Autowired
	private CashService cashService;
	@GetMapping("/removeCashBook")
	public String removeCashBook(HttpSession session) {
		return "removeCashBook";
	}
	@GetMapping("/modifyCashBook")
	public String modifyCashBook(HttpSession session) {
		return "modifyCashBook";
	}
	@GetMapping("/removeCash")
	public String removeCash(HttpSession session) {
		return "removeCash";
	}
	@GetMapping("/modifyCash")
	public String modifyCash(HttpSession session) {
		return "modifyCash";
	}
	@GetMapping("/addCash")
	public String addCash(HttpSession session) {
		return "addCash";
	}
	@GetMapping("/addCashBook")
	public String addCashBook(HttpSession session) {
		return "addCashBook";
	}
	@GetMapping("/getCashListByMonth")
	public String getCashListByMonth(HttpSession session,
			Model model,
			@RequestParam(value="day", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate day) {
		/*
		if(session.getAttribute("loginMember") == null) {
			return "redirect:/";
		}
		*/
		System.out.println("day:"+day);
		Calendar cDay = Calendar.getInstance(); // 오늘
		if(day == null) {
			day = LocalDate.now();
		} else {
			cDay.set(day.getYear(), day.getMonthValue()-1, day.getDayOfMonth()); // 오늘날짜에서 day값과 동일한 값으로...
		}
		String memberId = ((LoginMember)session.getAttribute("loginMember")).getMemberId();
		int year = cDay.get(Calendar.YEAR);
		int month = cDay.get(Calendar.MONTH)+1;
		List<DayAndPrice> dayAndPriceList = cashService.getDayAndPriceList(memberId, year, month);
		System.out.println(dayAndPriceList+"<--dayAndPriceList");

		model.addAttribute("dayAndPriceList", dayAndPriceList);
		model.addAttribute("day", day);
		model.addAttribute("month", cDay.get(Calendar.MONTH)+1); // 월
		model.addAttribute("lastDay", cDay.getActualMaximum(Calendar.DATE)); // 마지막 일

		Calendar firstDay = cDay;
		firstDay.set(Calendar.DATE, 1); // cDay에서 일만 1일로 변경
		System.out.println(firstDay.get(Calendar.YEAR)+","+(firstDay.get(Calendar.MONTH)+1)+","+firstDay.get(Calendar.DATE));
		System.out.println("firstDay.get(Calendar.DAY_OF_WEEK):"+firstDay.get(Calendar.DAY_OF_WEEK));// 1 일요일, 2월요일, ....7 토요일
		model.addAttribute("firstDayOfWeek", firstDay.get(Calendar.DAY_OF_WEEK));
		return "getCashListByMonth";
	}
	
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
