package com.bukkeubook.book.manage.model.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bukkeubook.book.manage.model.repository.EmpSalaryRepository;


@Service
public class SalaryService {
   
   private final EmpSalaryRepository empSalaryRepository;
   private final ModelMapper modelMapper;
   
   @Autowired
   public SalaryService(EmpSalaryRepository empSalaryRepository, ModelMapper modelMapper) {
      this.empSalaryRepository = empSalaryRepository;
      this.modelMapper = modelMapper;
   }
   
   
  

}