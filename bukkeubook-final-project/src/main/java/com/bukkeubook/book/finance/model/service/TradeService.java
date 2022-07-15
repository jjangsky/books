package com.bukkeubook.book.finance.model.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bukkeubook.book.finance.model.repository.TradeRepository;

@Service
public class TradeService {

	private final TradeRepository tradeRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public TradeService(TradeRepository tradeRepository, ModelMapper modelMapper) {
		this.tradeRepository = tradeRepository;
		this.modelMapper = modelMapper;
	}
}
