package com.biz.ems.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.biz.ems.config.StaticString;
import com.biz.ems.domain.EmailVO;
import com.biz.ems.domain.NaverMember;
import com.biz.ems.mapper.MybatisEmailDao;
import com.biz.ems.service.SaveMailService;
import com.biz.ems.service.SendMailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SessionAttributes("emailVO")
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/ems")
public class EmailController {
	private final MybatisEmailDao mybatisEmailDao;
	private final SendMailService xMailService;
	private final SaveMailService mailService;
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
	
	@RequestMapping(value = "/list",method=RequestMethod.GET)
	public String list(Model model) {
		mybatisEmailDao.createTable(StaticString.string1);
		List<EmailVO> mailList=mailService.selectAll();
		model.addAttribute("LIST", mailList);
		return "home";
	}
	
	@RequestMapping(value = "/input",method = RequestMethod.GET)
	public String input(@ModelAttribute("emailVO") EmailVO emailVO,Model model,SessionStatus status
			,HttpSession httpSession) {
		NaverMember memberVO=(NaverMember) httpSession.getAttribute("MEMBER");
		if(memberVO==null) {
			model.addAttribute("LOGIN", "NO");
			return "home";
		}
		emailVO=makeEmailVO();
		
		//naver에서 가져온 email이 실제 로그인한 email이 아니고 
		//복구용 email이기 때문에
		//실제 email보내기가 안될수도 있다.
		emailVO.setFrom_email(memberVO.getEmail());
		emailVO.setFrom_name(memberVO.getName());
		//session 지워주기
		//status.setComplete();
		model.addAttribute("emailVO", emailVO);
		model.addAttribute("BODY","WRITE");
		return "home";
	}
	
	@RequestMapping(value = "/input",method=RequestMethod.POST)
	public String input(@ModelAttribute("emailVO") EmailVO emailVO) {
		xMailService.sendMail(emailVO);
		mailService.insert(emailVO);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/view/{ems_seq}",method=RequestMethod.GET)
	public String view(@ModelAttribute("emailVO") EmailVO emailVO, @PathVariable("ems_seq") String ems_seq,Model model) {
		emailVO=mailService.findBySeq(Long.valueOf(ems_seq));
		model.addAttribute("BODY", "VIEW");
		model.addAttribute("emailVO", emailVO);
		return "home";
	}
	
	@RequestMapping(value = "/update/{ems_seq}",method=RequestMethod.GET)
	public String update(@ModelAttribute("emailVO") EmailVO emailVO, @PathVariable("ems_seq") String ems_seq,Model model) {
		emailVO=mailService.findBySeq(Long.valueOf(ems_seq));
		model.addAttribute("BODY", "WRITE");
		model.addAttribute("emailVO", emailVO);
		return "home";
	}
	
	@RequestMapping(value = "/update/{ems_seq}",method=RequestMethod.POST)
	public String update(@ModelAttribute("emailVO") EmailVO emailVO, @PathVariable("ems_seq") String ems_seq) {
		mailService.update(emailVO);
		return "redirect:/ems/list";
	}
	
	@RequestMapping(value = "/delete/{ems_seq}",method=RequestMethod.GET)
	public String delete(@ModelAttribute("emailVO") EmailVO emailVO, @PathVariable("ems_seq") String ems_seq) {
		emailVO=mailService.findBySeq(Long.valueOf(ems_seq));
		mailService.delete(Long.valueOf(ems_seq));
		return "redirect:/ems/list";
	}
}
