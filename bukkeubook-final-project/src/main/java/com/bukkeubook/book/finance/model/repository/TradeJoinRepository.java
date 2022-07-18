package com.bukkeubook.book.finance.model.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bukkeubook.book.finance.model.entity.TradeAndClientAndBookAndEmp;

@Repository
public interface TradeJoinRepository extends JpaRepository<TradeAndClientAndBookAndEmp, Integer>{

	List<TradeAndClientAndBookAndEmp> findByTlDateContaining(String searchValue, Pageable paging);
}
