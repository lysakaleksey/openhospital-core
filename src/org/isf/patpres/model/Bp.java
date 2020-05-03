package org.isf.patpres.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@SuppressWarnings("serial")
@Embeddable
public class Bp{
	private Integer systole;
	private Integer diastole;

	public Bp() {
	}

	public Bp(Integer systole, Integer diastole) {
		this.systole = systole;
		this.diastole = diastole;
	}

	public Integer getSystole() {
		return systole;
	}

	public void setSystole(Integer systole) {
		this.systole = systole;
	}

	public Integer getDiastole() {
		return diastole;
	}

	public void setDiastole(Integer diastole) {
		this.diastole = diastole;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Bp bp = (Bp) o;

		if (systole != null ? !systole.equals(bp.systole) : bp.systole != null) return false;
		return diastole != null ? diastole.equals(bp.diastole) : bp.diastole == null;
	}

	@Override
	public int hashCode() {
		int result = systole != null ? systole.hashCode() : 0;
		result = 31 * result + (diastole != null ? diastole.hashCode() : 0);
		return result;
	}
}
