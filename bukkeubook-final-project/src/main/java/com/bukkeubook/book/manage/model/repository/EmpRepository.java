package com.bukkeubook.book.manage.model.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import com.bukkeubook.book.manage.model.dto.EmpDTO;
import com.bukkeubook.book.manage.model.entity.Emp;
import com.bukkeubook.book.manage.model.entity.EmpAndDept;

@Repository
public interface EmpRepository extends JpaRepository<EmpAndDept, Integer>{
	
	/* 퇴사여부 Y 인 사원 조회 */
	List<EmpAndDept> findByEmpEndYn(String empEndYn);

	/* 검색기능 & 페이징처리 */
	int countByEmpNameContaining(String searchValue);
	
	int countByempJobCodeContaining(String searchValue);

	List<EmpAndDept> findByEmpNameContaining(String searchValue, Pageable paging);

	List<EmpAndDept> findByEmpJobCodeContaining(String searchValue, Pageable paging);






	
}

