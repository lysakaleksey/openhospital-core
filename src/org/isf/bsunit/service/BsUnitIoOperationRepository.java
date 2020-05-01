package org.isf.bsunit.service;

import org.isf.bsunit.model.BsUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BsUnitIoOperationRepository extends JpaRepository<BsUnit, String> {
	List<BsUnit> findAllByOrderByDescriptionAsc();
}