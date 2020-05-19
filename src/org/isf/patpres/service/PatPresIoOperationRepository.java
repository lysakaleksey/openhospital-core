package org.isf.patpres.service;

import org.isf.patpres.model.PatientPresentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatPresIoOperationRepository extends JpaRepository<PatientPresentation, Integer>, PatPresIoOperationRepositoryCustom {
	List<PatientPresentation> findByPatientCode(int code);
}