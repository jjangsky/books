package com.bukkeubook.book.document.model.service;

import java.util.List;

import com.bukkeubook.book.document.model.dto.DeptDTO;
import com.bukkeubook.book.document.model.dto.EmpDTO;
import com.bukkeubook.book.document.model.dto.FormCateDTO;

public interface DocService {

	List<FormCateDTO> findDocFormList();

	List<DeptDTO> findDept();

	List<EmpDTO> findEmp(int dept);

}
