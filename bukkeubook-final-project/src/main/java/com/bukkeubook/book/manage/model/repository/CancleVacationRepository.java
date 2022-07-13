package com.bukkeubook.book.manage.model.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bukkeubook.book.manage.model.entity.CancleVacAndAppVac;

@Repository
public interface CancleVacationRepository extends JpaRepository<CancleVacAndAppVac, Integer>{

	List<CancleVacAndAppVac> findAllCancleRest(Sort by);

}
