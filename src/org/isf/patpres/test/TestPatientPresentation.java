package org.isf.patpres.test;

import org.isf.patient.model.Patient;
import org.isf.patpres.model.PatientPresentation;
import org.isf.patpres.model.Vitals;
import org.isf.utils.exception.OHException;

import javax.persistence.Column;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class TestPatientPresentation {
	private final GregorianCalendar previousConsult = new GregorianCalendar(1984, Calendar.AUGUST, 1);
	private final GregorianCalendar presentationDate = new GregorianCalendar(1984, Calendar.AUGUST, 2);
	private final GregorianCalendar consultationEnd = new GregorianCalendar(1984, Calendar.AUGUST, 3);
	private final int code = 0;
	private final String referredFrom = "referredFrom";
	private final String patientAilmentDescription = "patientAilmentDescription";
	private final String doctorsAilmentDescription = "doctorsAilmentDescription";
	private final String specificSymptoms = "specificSymptoms";
	private final String diagnosis = "diagnosis";
	private final String prognosis = "prognosis";
	private final String patientAdvice = "patientAdvice";
	private final String prescribed = "prescribed";
	private final String followUp = "followUp";
	private final String referredTo = "referredTo";
	private final String summary = "summary";

	public PatientPresentation setup(Patient patient, Vitals vitals, boolean usingSet) throws OHException {
		PatientPresentation patientPresentation;

		if (usingSet) {
			patientPresentation = new PatientPresentation();
			_setParameters(patient, vitals, patientPresentation);
		} else {
			patientPresentation = new PatientPresentation(code, patient, vitals, presentationDate, consultationEnd, previousConsult,
				referredFrom, patientAilmentDescription, doctorsAilmentDescription, specificSymptoms,
				diagnosis, prognosis, patientAdvice, prescribed, followUp, referredTo, summary);
		}
		return patientPresentation;
	}

	public void _setParameters(Patient patient, Vitals vitals, PatientPresentation patientPresentation) {
		patientPresentation.setCode(code);
		patientPresentation.setPatient(patient);
		patientPresentation.setVitals(vitals);
		patientPresentation.setPresentationDate(presentationDate);
		patientPresentation.setConsultationEnd(consultationEnd);
		patientPresentation.setPreviousConsult(previousConsult);
		patientPresentation.setReferredFrom(referredFrom);
		patientPresentation.setPatientAilmentDescription(patientAilmentDescription);
		patientPresentation.setDoctorsAilmentDescription(doctorsAilmentDescription);
		patientPresentation.setSpecificSymptoms(specificSymptoms);
		patientPresentation.setDiagnosis(diagnosis);
		patientPresentation.setPrognosis(prognosis);
		patientPresentation.setPatientAdvice(patientAdvice);
		patientPresentation.setPrescribed(prescribed);
		patientPresentation.setFollowUp(followUp);
		patientPresentation.setReferredTo(referredTo);
		patientPresentation.setSummary(summary);
	}

	public void check(PatientPresentation patientPresentation) {
		assertEquals(previousConsult, patientPresentation.getPreviousConsult());
		assertEquals(presentationDate, patientPresentation.getPresentationDate());
		assertEquals(consultationEnd, patientPresentation.getConsultationEnd());
	}
}
