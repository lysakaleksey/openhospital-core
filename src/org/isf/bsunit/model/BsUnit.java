package org.isf.bsunit.model;

import org.isf.utils.db.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="BSUNIT")
@EntityListeners(AuditingEntityListener.class)
@AttributeOverrides({
    @AttributeOverride(name="createdBy", column=@Column(name="BSU_CREATED_BY")),
    @AttributeOverride(name="createdDate", column=@Column(name="BSU_CREATED_DATE")),
    @AttributeOverride(name="lastModifiedBy", column=@Column(name="BSU_LAST_MODIFIED_BY")),
    @AttributeOverride(name="active", column=@Column(name="BSU_ACTIVE")),
    @AttributeOverride(name="lastModifiedDate", column=@Column(name="BSU_LAST_MODIFIED_DATE"))
})
public class BsUnit extends Auditable<String>
{
	@Id
	@Column(name="BSU_ID_A")
    private String code;

	@NotNull
	@Column(name="BSU_DESC")
    private String description;

	public BsUnit() {
	}

	public BsUnit(String code, String description) {
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

		BsUnit bsUnit = (BsUnit) o;

		if (code != null ? !code.equals(bsUnit.code) : bsUnit.code != null) return false;
		return description != null ? description.equals(bsUnit.description) : bsUnit.description == null;
	}

	@Override
	public int hashCode() {
		int result = code != null ? code.hashCode() : 0;
		result = 31 * result + (description != null ? description.hashCode() : 0);
		return result;
	}
}
