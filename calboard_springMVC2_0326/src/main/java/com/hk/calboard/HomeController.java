package com.hk.calboard;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ProcessBuilder.Redirect;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.hk.calboard.service.ICalService;
import com.hk.caldtos.CalDto;
import com.hk.utils.Util;


@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private ICalService calservice;

	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/calendar.do", method = RequestMethod.GET)
	public String calendar(Locale locale, Model model,String year, String month) {
		logger.info("달력보기{}.", locale);
		String id="kbj";
		if(year==null) {
			Calendar cal=Calendar.getInstance();
			year=cal.get(Calendar.YEAR)+"";
			month=(cal.get(Calendar.MONTH)+1)+"";
		}
		String yyyyMM=year+Util.isTwo(String.valueOf(month));
		
		List<CalDto>list = calservice.getCalViewList(id, yyyyMM);
		model.addAttribute("list", list);
		
		return "calendar";
	}
	

	@RequestMapping(value = "/callist.do", method = RequestMethod.GET)
	public String callist(HttpServletRequest request, Locale locale, Model model, String year, String month, String date) {
		logger.info("일정보기{}.", locale);
		request.getSession().setAttribute("year", year);
		request.getSession().setAttribute("month", month);
		request.getSession().setAttribute("date", date);
		
		String id="kbj";
		String yyyyMMdd=year+Util.isTwo(month)+Util.isTwo(date);
		
		List<CalDto> list = calservice.getCalList(id, yyyyMMdd);
		model.addAttribute("list", list);
		
		
		return "callist";
	}
	
	@RequestMapping(value = "/insertform.do", method = RequestMethod.GET)
	public String insertform(Locale locale, Model model) {
		logger.info("일정추가로 이동하기{}.", locale);
		
		return "insertCal";
	}
	
	@RequestMapping(value = "/insertcal.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String insertcal(Locale locale, Model model, 
			String year, String month, String date, String hour, String min, 
			String id,String title,String content) {
		logger.info("일정추가하기{}.", locale);
		
		String mdate = year+Util.isTwo(month)
							+Util.isTwo(date)
							+Util.isTwo(hour)
							+Util.isTwo(min);
		CalDto dto = new CalDto(0,id,title,content,mdate,null);
		
		boolean isS = calservice.insertCalboard(dto);
		
		if (isS) {
			return "redirect:calendar.do?year="+year+"&month="+month;
		}else {
			model.addAttribute("msg", "일정추가실패");
			return "error";
		}
		
	}
	
	@RequestMapping(value = "/caldetail.do", method = RequestMethod.GET)
	public String caldetail(Locale locale, Model model, int seq) {
		logger.info("달력일정 상세보기{}.", locale);
		
		CalDto dto = calservice.getCalBoard(seq);
		model.addAttribute("dto", dto);
		
		
		return "caldetail";
	}
	
	@RequestMapping(value = "/calupdateform.do", method = RequestMethod.GET)
	public String calupdateform(Locale locale, Model model, int seq) {
		logger.info("달력일정 수정폼가기{}.", locale);
		
		CalDto dto = calservice.getCalBoard(seq);
		model.addAttribute("dto", dto);
		
		return "calupdate";
	}
	
	@RequestMapping(value = "/calupdate.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String calupdate(HttpServletRequest request, Locale locale, Model model, 
			String year, String month, String date, String hour, String min, 
			String id,String title,String content) {
		logger.info("달력 일정수정하기{}.", locale);
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		String mdate = year+Util.isTwo(month)
							+Util.isTwo(date)
							+Util.isTwo(hour)
							+Util.isTwo(min);
		CalDto dto = new CalDto(seq,id,title,content,mdate,null);
		
		boolean isS = calservice.updateCalBoard(dto);
		System.out.println("calupdate dto값은?"+dto);
		if (isS) {
			return "redirect:caldetail.do?seq="+dto.getSeq();
		}else {
			model.addAttribute("msg", "일정추가실패");
			return "error";
		}
	}
	
	@RequestMapping(value = "/muldel.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String mulde(HttpServletRequest request, Locale locale, Model model, String[] chk) {
		logger.info("달력일정 삭제하기{}.", locale);
		String[] seqs = chk;
		boolean isS = calservice.calMulDel(seqs);
			String year = (String) request.getSession().getAttribute("year");
			String month = (String) request.getSession().getAttribute("month");
			String date = (String) request.getSession().getAttribute("date");
		
		if (isS) {
			return "redirect:callist.do?year="+year+"&month="+month+"&date="+date;
			
		}else {
			model.addAttribute("msg", "일정삭제실패");
			return "error";
		}
		
	}
	
	@RequestMapping(value = "/CalCountAjax.do", method =  {RequestMethod.GET, RequestMethod.POST})
	public void CalCountAjax(HttpServletResponse response, Locale locale, Model model, String yyyyMMdd) throws IOException {
		logger.info("달력일정 아이작스{}.", locale);
		
		String id = "kbj";
		int count = calservice.getCalViewCount(id, yyyyMMdd);
		PrintWriter pw=response.getWriter();
		pw.println(count+"");
		
		
	}
	
}