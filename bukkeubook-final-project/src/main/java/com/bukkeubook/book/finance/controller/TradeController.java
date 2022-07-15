package com.bukkeubook.book.finance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bukkeubook.book.finance.model.service.TradeService;

@Controller
@RequestMapping("/trade")
public class TradeController {

	private final TradeService tradeService;
	
	@Autowired
	public TradeController(TradeService tradeService) {
		this.tradeService = tradeService;
	}
}
