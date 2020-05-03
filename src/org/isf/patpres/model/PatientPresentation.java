package org.isf.patpres.model;

import org.isf.patient.model.Patient;
import org.isf.utils.db.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.GregorianCalendar;

@Entity
@Table(name = "PATIENTPRESENTATION")
@EntityListeners(AuditingEntityListener.class)
@AttributeOverrides({
	@AttributeOverride(name = "createdBy", column = @Column(name = "PPR_CREATED_BY")),
	@AttributeOverride(name = "createdDate", column = @Column(name = "PPR_CREATED_DATE")),
	@AttributeOverride(name = "lastModifiedBy", column = @Column(name = "PPR_LAST_MODIFIED_BY")),
	@AttributeOverride(name = "active", column = @Column(name = "PPR_ACTIVE")),
	@AttributeOverride(name = "lastModifiedDate", column = @Column(name = "PPR_LAST_MODIFIED_DATE"))
})
public class PatientPresentation extends Auditable<String> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PPR_ID")
	private int code;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "PPR_PAT_ID")
	private Patient patient;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "weight", column = @Column(name = "PPR_VITALS_WEIGHT")),
		@AttributeOverride(name = "height", column = @Column(name = "PPR_VITALS_HEIGHT")),
		@AttributeOverride(name = "bloodSugar", column = @Column(name = "PPR_VITALS_BLOOD_SUGAR")),
		@AttributeOverride(name = "temperature", column = @Column(name = "PPR_VITALS_TEMPERATURE")),
		@AttributeOverride(name = "bsUnit", column = @Column(name = "PPR_VITALS_BS_UNIT")),
		@AttributeOverride(name = "tempUnit", column = @Column(name = "PPR_VITALS_TEMP_UNIT")),
		@AttributeOverride(name = "bp.systole", column = @Column(name = "PPR_VITALS_BP_SYSTOLE")),
		@AttributeOverride(name = "bp.diastole", column = @Column(name = "PPR_VITALS_BP_DIASTOLE"))
	})
	private Vitals vitals;

	@Column(name = "PPR_PRES_DATE")
	private GregorianCalendar presentationDate;

	@Column(name = "PPR_CONS_DATE")
	private GregorianCalendar consultationEnd;

	@Column(name = "PPR_PREV_DATE")
	private GregorianCalendar previousConsult;

	@Max(value = 100)
	@Column(name = "PPR_REFERRED_FROM")
	private String referredFrom;

	@Max(value = 65535)
	@Column(name = "PPR_PAT_AILM_DESC")
	private String patientAilmentDescription;

	@Max(value = 65535)
	@Column(name = "PPR_DOC_AILM_DESC")
	private String doctorsAilmentDescription;

	@Max(value = 65535)
	@Column(name = "PPR_SPEC_SYMPTOMS")
	private String specificSymptoms;

	@Max(value = 65535)
	@Column(name = "PPR_DIAGNOSIS")
	private String diagnosis;

	@Max(value = 65535)
	@Column(name = "PPR_PROGNOSIS")
	private String prognosis;

	@Max(value = 65535)
	@Column(name = "PPR_ADVICE")
	private String patientAdvice;

	@Max(value = 65535)
	@Column(name = "PPR_PRESCRIBED")
	private String prescribed;

	@Max(value = 65535)
	@Column(name = "PPR_FOLLOW_UP")
	private String followUp;

	@Max(value = 100)
	@Column(name = "PPR_REFERRED_TO")
	private String referredTo;

	@NotNull
	@Max(value = 65535)
	@Column(name = "PPR_SUMMARY")
	private String summary;

	public PatientPresentation() {
	}

	public PatientPresentation(int code, Patient patient, Vitals vitals, GregorianCalendar presentationDate, GregorianCalendar consultationEnd,
							   GregorianCalendar previousConsult, String referredFrom, String patientAilmentDescription, String doctorsAilmentDescription,
							   String specificSymptoms, String diagnosis, String prognosis, String patientAdvice, String prescribed, String followUp,
							   String referredTo, String summary) {
		this.code = code;
		this.patient = patient;
		this.vitals = vitals;
		this.presentationDate = presentationDate;
		this.consultationEnd = consultationEnd;
		this.previousConsult = previousConsult;
		this.referredFrom = referredFrom;
		this.patientAilmentDescription = patientAilmentDescription;
		this.doctorsAilmentDescription = doctorsAilmentDescription;
		this.specificSymptoms = specificSymptoms;
		this.diagnosis = diagnosis;
		this.prognosis = prognosis;
		this.patientAdvice = patientAdvice;
		this.prescribed = prescribed;
		this.followUp = followUp;
		this.referredTo = referredTo;
		this.summary = summary;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Vitals getVitals() {
		return vitals;
	}

	public void setVitals(Vitals vitals) {
		this.vitals = vitals;
	}

	public GregorianCalendar getPresentationDate() {
		return presentationDate;
	}

	public void setPresentationDate(GregorianCalendar presentationDate) {
		this.presentationDate = presentationDate;
	}

	public GregorianCalendar getConsultationEnd() {
		return consultationEnd;
	}

	public void setConsultationEnd(GregorianCalendar consultationEnd) {
		this.consultationEnd = consultationEnd;
	}

	public GregorianCalendar getPreviousConsult() {
		return previousConsult;
	}

	public void setPreviousConsult(GregorianCalendar previousConsult) {
		this.previousConsult = previousConsult;
	}

	public String getReferredFrom() {
		return referredFrom;
	}

	public void setReferredFrom(String referredFrom) {
		this.referredFrom = referredFrom;
	}

	public String getPatientAilmentDescription() {
		return patientAilmentDescription;
	}

	public void setPatientAilmentDescription(String patientAilmentDescription) {
		this.patientAilmentDescription = patientAilmentDescription;
	}

	public String getDoctorsAilmentDescription() {
		return doctorsAilmentDescription;
	}

	public void setDoctorsAilmentDescription(String doctorsAilmentDescription) {
		this.doctorsAilmentDescription = doctorsAilmentDescription;
	}

	public String getSpecificSymptoms() {
		return specificSymptoms;
	}

	public void setSpecificSymptoms(String specificSymptoms) {
		this.specificSymptoms = specificSymptoms;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getPrognosis() {
		return prognosis;
	}

	public void setPrognosis(String prognosis) {
		this.prognosis = prognosis;
	}

	public String getPatientAdvice() {
		return patientAdvice;
	}

	public void setPatientAdvice(String patientAdvice) {
		this.patientAdvice = patientAdvice;
	}

	public String getPrescribed() {
		return prescribed;
	}

	public void setPrescribed(String prescribed) {
		this.prescribed = prescribed;
	}

	public String getFollowUp() {
		return followUp;
	}

	public void setFollowUp(String followUp) {
		this.followUp = followUp;
	}

	public String getReferredTo() {
		return referredTo;
	}

	public void setReferredTo(String referredTo) {
		this.referredTo = referredTo;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		PatientPresentation that = (PatientPresentation) o;

		if (code != that.code) return false;
		return patient != null ? patient.equals(that.patient) : that.patient == null;
	}

	@Override
	public int hashCode() {
		int result = code;
		result = 31 * result + (patient != null ? patient.hashCode() : 0);
		return result;
	}
}
