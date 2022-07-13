package com.bukkeubook.book.manage.model.repository;

import java.util.List;

import javax.persistence.TypedQuery;

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
//	@Query("SELECT u FROM EmpAndDept u WHERE u.empEndYn = 'Y'")
//	List<EmpAndDept> findLeaveEmpList(String empEndYn);
	
//	String jpql = "SELECT m.EMP_END_YN FROM EmpAndDept as m WHERE m.EMP_END_YN = 'Y'";
//	TypedQuery<EmpAndDept> query = Emp.createQuery(jpql,EmpAndDept.class);
//	List<EmpAndDept> leaveEmpList = query.getResultList();

	List<EmpAndDept> findByEmpEndYn(String empEndYn);



	
}

