package org.isf.patpres.test;

import org.isf.patpres.model.PatientPresentation;
import org.isf.utils.db.DbJpaUtil;
import org.isf.utils.exception.OHException;

import java.util.List;

public class TestPatientPresentationContext {
	private static List<PatientPresentation> savedPatientPresentation;

	@SuppressWarnings("unchecked")
	public void saveAll(DbJpaUtil jpa) throws OHException {
		jpa.beginTransaction();
		jpa.createQuery("SELECT * FROM PATIENTPRESENTATION", PatientPresentation.class, false);
		savedPatientPresentation = (List<PatientPresentation>) jpa.getList();
		jpa.commitTransaction();
	}

	public List<PatientPresentation> getAllSaved() throws OHException {
		return savedPatientPresentation;
	}

	@SuppressWarnings("unchecked")
	public void deleteNews(DbJpaUtil jpa) throws OHException {
		jpa.beginTransaction();
		jpa.createQuery("SELECT * FROM PATIENTPRESENTATION", PatientPresentation.class, false);
		List<PatientPresentation> PatientPresentations = (List<PatientPresentation>) jpa.getList();
		for (PatientPresentation patientVaccine : PatientPresentations) {
			int index = savedPatientPresentation.indexOf(patientVaccine);
			if (index == -1) {
				jpa.remove(patientVaccine);
			}
		}
		jpa.commitTransaction();
	}
}
