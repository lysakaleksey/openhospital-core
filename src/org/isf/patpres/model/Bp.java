package org.isf.patpres.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@SuppressWarnings("serial")
@Embeddable
public class Bp{
	private int systole;
	private int diastole;

	public Bp() {
	}

	public Bp(int systole, int diastole) {
		this.systole = systole;
		this.diastole = diastole;
	}

	public int getSystole() {
		return systole;
	}

	public void setSystole(int systole) {
		this.systole = systole;
	}

	public int getDiastole() {
		return diastole;
	}

	public void setDiastole(int diastole) {
		this.diastole = diastole;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Bp bp = (Bp) o;

		if (systole != bp.systole) return false;
		return diastole == bp.diastole;
	}

	@Override
	public int hashCode() {
		int result = systole;
		result = 31 * result + diastole;
		return result;
	}
}
