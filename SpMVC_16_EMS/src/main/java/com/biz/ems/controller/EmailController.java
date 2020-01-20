package com.biz.ems.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.biz.ems.domain.EmailVO;
import com.biz.ems.service.SendMailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SessionAttributes("emailVO")
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/ems")
public class EmailController {
	private final SendMailService xMailService;
	/*
	 * ModelAttribute 생성자 method
	 * Controller에 ModelAttribute 객체가 없거나 null인 상태면
	 * 이 method를 실행하여 emailVO를 만든다.
	 * 
	 * 하지만, 현재 상태에서 한번이라도 이 method가 호출되어 emailVO가 생성된 상태라면 다시 method가 수행되지 않는다.
	 */
	@ModelAttribute("emailVO")
	public EmailVO makeEmailVO() {
		EmailVO emailVO=new EmailVO();
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat st=new SimpleDateFormat("HH:mm:ss");
		Date date=new Date();
		String curDate=sd.format(date);
		String curTime=st.format(date);
		emailVO.setSend_date(curDate);
		emailVO.setSend_time(curTime);
		return emailVO;
	}
	@RequestMapping(value = "/input",method = RequestMethod.GET)
	public String input(@ModelAttribute("emailVO") EmailVO emailVO,Model model,SessionStatus status) {
		emailVO=makeEmailVO();
		
		//session 지워주기
		//status.setComplete();
		model.addAttribute("emailVO", emailVO);
		model.addAttribute("BODY","WRITE");
		return "home";
	}
	
	@RequestMapping(value = "/input",method=RequestMethod.POST)
	public String input(@ModelAttribute("emailVO") EmailVO emailVO) {
		log.debug("!!! vo in controller:"+emailVO.toString());
		xMailService.sendMail(emailVO);
		return "redirect:/";
	}
}