package org.isf.tempunit.model;

import org.isf.utils.db.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="TEMPUNIT")
@EntityListeners(AuditingEntityListener.class)
@AttributeOverrides({
    @AttributeOverride(name="createdBy", column=@Column(name="TPU_CREATED_BY")),
    @AttributeOverride(name="createdDate", column=@Column(name="TPU_CREATED_DATE")),
    @AttributeOverride(name="lastModifiedBy", column=@Column(name="TPU_LAST_MODIFIED_BY")),
    @AttributeOverride(name="active", column=@Column(name="TPU_ACTIVE")),
    @AttributeOverride(name="lastModifiedDate", column=@Column(name="TPU_LAST_MODIFIED_DATE"))
})
public class TempUnit extends Auditable<String>
{
	@Id
	@Column(name="TPU_ID_A")
    private String code;

	@NotNull
	@Column(name="TPU_DESC")
    private String description;

	public TempUnit() {
	}

	public TempUnit(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		TempUnit tempUnit = (TempUnit) o;

		if (code != null ? !code.equals(tempUnit.code) : tempUnit.code != null) return false;
		return description != null ? description.equals(tempUnit.description) : tempUnit.description == null;
	}

	@Override
	public int hashCode() {
		int result = code != null ? code.hashCode() : 0;
		result = 31 * result + (description != null ? description.hashCode() : 0);
		return result;
	}
}
