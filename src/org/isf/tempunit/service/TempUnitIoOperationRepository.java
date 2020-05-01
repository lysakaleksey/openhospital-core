package org.isf.tempunit.service;

import org.isf.tempunit.model.TempUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TempUnitIoOperationRepository extends JpaRepository<TempUnit, String> {
	List<TempUnit> findAllByOrderByDescriptionAsc();
}