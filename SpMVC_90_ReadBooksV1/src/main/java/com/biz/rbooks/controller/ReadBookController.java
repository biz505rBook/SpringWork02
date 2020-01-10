package com.biz.rbooks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.biz.rbooks.domain.BooksVO;
import com.biz.rbooks.domain.DetailBookViewVO;
import com.biz.rbooks.domain.SimpleViewVO;
import com.biz.rbooks.service.ReadBookService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/readbook")
public class ReadBookController {
	private final ReadBookService readBookService;
	
	@Autowired
	public ReadBookController(ReadBookService readBookService) {
		super();
		this.readBookService = readBookService;
	}

	@RequestMapping(value = "/showalllist",method = RequestMethod.GET)
	public String showAllList(Model model) {
		List<SimpleViewVO> simepleViewList=readBookService.showAllSummary();
		log.debug("!!! simepleViewList in controller:"+simepleViewList.toString());
		model.addAttribute("simepleViewList", simepleViewList);
		return "simpleviewsummary";
	}
	
	@RequestMapping(value = "/viewdetail",method=RequestMethod.GET)
	public String viewDetail(@RequestParam("rb_seq")String strRb_seq, @RequestParam("b_code") String b_code
			, @RequestParam("m_id") String _m_id, Model model) {
		long rb_seq=-1;
		try {
			rb_seq=Long.valueOf(strRb_seq);
		} catch (Exception e) {
			// TODO: handle exception
		}
		DetailBookViewVO detailBookViewVO=readBookService.viewDetailOfBook(rb_seq,b_code,_m_id);
		model.addAttribute("detailBookViewVO", detailBookViewVO);
		return "/detailview";
	}
	
	@RequestMapping(value = "/viewAllBooks",method=RequestMethod.GET)
	public String viewAllBooks(Model model) {
		List<BooksVO> booksList=readBookService.viewAllBooks();
		model.addAttribute("booksList", booksList);
		return "/viewallbooks";
	}
	
}
