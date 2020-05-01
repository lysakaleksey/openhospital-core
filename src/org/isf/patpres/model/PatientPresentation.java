package org.isf.patpres.model;

import org.isf.patient.model.Patient;
import org.isf.utils.db.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.GregorianCalendar;

@Entity
@Table(name="PATIENTPRESENTATION")
@EntityListeners(AuditingEntityListener.class)
@AttributeOverrides({
	@AttributeOverride(name="createdBy", column=@Column(name="PPR_CREATED_BY")),
	@AttributeOverride(name="createdDate", column=@Column(name="PPR_CREATED_DATE")),
	@AttributeOverride(name="lastModifiedBy", column=@Column(name="PPR_LAST_MODIFIED_BY")),
	@AttributeOverride(name="active", column=@Column(name="PPR_ACTIVE")),
	@AttributeOverride(name="lastModifiedDate", column=@Column(name="PPR_LAST_MODIFIED_DATE"))
})
public class PatientPresentation extends Auditable<String>  {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PPR_ID")
	private int code;

	@NotNull
	@ManyToOne
	@JoinColumn(name="PPR_PAT_ID")
	private Patient patient;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="weight", column=@Column(name="PPR_VITALS_WEIGHT")),
		@AttributeOverride(name="height", column=@Column(name="PPR_VITALS_HEIGHT")),
		@AttributeOverride(name="bloodSugar", column=@Column(name="PPR_VITALS_BLOOD_SUGAR")),
		@AttributeOverride(name="temperature", column=@Column(name="PPR_VITALS_TEMPERATURE")),
		@AttributeOverride(name="bsUnit", column=@Column(name="PPR_VITALS_BS_UNIT")),
		@AttributeOverride(name="tempUnit", column=@Column(name="PPR_VITALS_TEMP_UNIT")),
		@AttributeOverride(name="bp.systole", column=@Column(name="PPR_VITALS_BP_SYSTOLE")),
		@AttributeOverride(name="bp.diastole", column=@Column(name="PPR_VITALS_BP_DIASTOLE"))
	})
	private Vitals vitals;

	@NotNull
	@Column(name="PPR_DATE")
	private GregorianCalendar vaccineDate;

}
