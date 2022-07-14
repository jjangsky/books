package com.bukkeubook.book.manage.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bukkeubook.book.manage.model.dto.joinDTO.DayOffAndEmpAndDeptDTO;
import com.bukkeubook.book.manage.model.service.EmpDayOffService;

@Controller
@RequestMapping("/empDayOff")
public class EmpDayOffController {
   
   private final EmpDayOffService empDayOffService;
   
   @Autowired
   public EmpDayOffController(EmpDayOffService empDayOffService) {
      this.empDayOffService = empDayOffService;
   }
   
   /* 직원 연차 조회 */
   @GetMapping("/empDayOffList")
   public ModelAndView findDayOffList(ModelAndView mv) {
      System.out.println("ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ");
      List<DayOffAndEmpAndDeptDTO> dayOffList= empDayOffService.findDayOffList();
      
      mv.addObject("dayOffList", dayOffList);
      mv.setViewName("/manage/empAnnual/empDayOffList"); 
      
      return mv;
   }
   

}